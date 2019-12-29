package io.inouty.jswdb.domain;

import io.inouty.jswdb.core.domain.GenreDto;
import io.inouty.jswdb.core.domain.MovieDto;
import io.inouty.jswdb.core.domain.Payload;
import io.inouty.jswdb.utils.DownloadUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MovieCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieCrawler.class);
    private static Payload.Builder builder;
    private String movieId;
    private Document itemDoc;
    /*    private Document releaseDoc;
        private Document castAndCrewDoc;*/
    private Pattern releaseDatePattern = Pattern.compile("^(?<day>[0-9]+)[\\s]+(?<month>[a-zA-Z]+)[\\s]+(?<year>[0-9]{4,})$");
    private Pattern yearMonthReleaseDatePattern = Pattern.compile("^(?<month>[a-zA-Z]+)[\\s]+(?<year>[0-9]{4,})$");
    private Pattern yearReleaseDatePattern = Pattern.compile("^(?<year>[0-9]{4,})$");
    private Pattern countryCodePattern = Pattern.compile("^(.+)region\\=(?<countryCode>.+)&(.+)$");
    private Pattern profileIdPattern = Pattern.compile("^\\/name\\/(?<profileId>.+)\\/.+$");
    private Pattern characterIdPattern = Pattern.compile("^.+\\/characters\\/(?<characterId>[a-zA-Z0-9]+).*$");

    @Value("#{T(java.util.regex.Pattern).compile('${imdb.patterns.year}')}")
    private Pattern releaseYearPattern;

    @Value("#{T(java.util.regex.Pattern).compile('${imdb.patterns.duration}')}")
    private Pattern durationPattern;


    @Value("#{T(java.util.regex.Pattern).compile('${imdb.patterns.profile-img-url}')}")
    private Pattern profileImgUrlPattern;

    private static final String profileImgReplacementRegex = "${pre}${u}100_CR,0,100,100_AL_.${ext}";

    @Value("${imdb.urls.release-info}")
    private String releaseInfoUrlTemplate;

    @Value("${imdb.urls.item-details}")
    private String movieUrlTemplate;

    @Value("${imdb.urls.full-cast-and-crew}")
    private String fullCastAndCrewUrlTemplate;

    private Document connect(String url) throws IOException {
        return Jsoup
                .connect(url)
                .maxBodySize(1024 * 1024 * 5)
                .get();
    }


    private void initialize(String movieId) throws IOException {
        this.movieId = movieId;
        this.builder = Payload.builder();
        this.itemDoc = connect(String.format(movieUrlTemplate, movieId));
        //this.releaseDoc = connect(String.format(releaseInfoUrlTemplate, movieId));
        //this.castAndCrewDoc = connect(String.format(fullCastAndCrewUrlTemplate, movieId));
    }

    private void nullify() {
        this.itemDoc = null;
    }

    public Payload run(String movieId, List<GenreDto> genres) throws IOException {
        this.initialize(movieId);
        this.setMovieBasicInfo();
        //this.setReleaseInfo();
        //this.setWriters();
        //this.setDirectors();
        //this.setCast();
        this.setGenres(genres);
        this.nullify();
        return builder.build();
    }

    private void setMovieBasicInfo() {
        final MovieDto.Builder movieBuilder = MovieDto.builder();
        //ID
        movieBuilder.withId(movieId);
        Element slateWrapper = itemDoc.selectFirst("#title-overview-widget .slate_wrapper");
        if (slateWrapper != null) {
            Element slateButtonE = slateWrapper.selectFirst(".slate a.slate_button");
            if (slateButtonE != null) {
                movieBuilder.withTrailerUrl(slateButtonE.attr("href"));
            }
            Element posterImgE = slateWrapper.selectFirst(".poster img");
            if (posterImgE != null) {
                final String movieImgUrl = posterImgE.attr("src");
                if (movieImgUrl != null && !movieImgUrl.trim().isEmpty()) {
                    String[] urlParts = movieImgUrl.split("._V1_");
                    if (urlParts.length > 0) {
                        movieBuilder.withProfileImg(urlParts[0]);
                    } else {
                        movieBuilder.withProfileImg(movieImgUrl);
                    }
                }
                //this.downloadProfileImg(movieImgUrl, movieId);
            }
        }

        Element titleYearE = itemDoc.getElementById("titleYear");
        if (titleYearE != null) {
            Element titleYearA = titleYearE.selectFirst("a");
            if (titleYearA != null) {
                String itemYearStr = titleYearA.text().trim();
                if (!itemYearStr.isEmpty()) {
                    movieBuilder.withYear(Integer.valueOf(itemYearStr));
                }
            }
        }

        Element titleWrapper = itemDoc.selectFirst(".title_wrapper");
        if (titleWrapper != null) {
            Element durationE = titleWrapper.selectFirst("time[datetime]");
            if (durationE != null) {
                Matcher matcher = durationPattern.matcher(durationE.text().trim());
                if (matcher.find()) {
                    Integer minutes = Integer.valueOf(matcher.group("amount"));
                    String durationStr = (minutes / 60) + ":" + (minutes % 60) + ":00";
                    movieBuilder.withDuration(Time.valueOf(durationStr));
                }
            }
            Element titleH1 = titleWrapper.getElementsByTag("h1").first();
            if (titleH1 != null) {
                titleH1.getElementById("titleYear").remove();
                movieBuilder.withTitle(titleH1.text());
            }
        }

        Element plotSummaryWrapper = itemDoc.selectFirst(".plot_summary_wrapper");
        if (plotSummaryWrapper != null) {
            Element plotSummary = plotSummaryWrapper.selectFirst(".plot_summary");
            if (plotSummary != null) {
                movieBuilder.withSummary(plotSummary.text());
            }
        }


        Element metaScoreE = itemDoc.selectFirst(".metacriticScore");
        if (metaScoreE != null) {
            Element metaScoreSpan = metaScoreE.getElementsByTag("span").first();
            if (metaScoreSpan != null) {
                movieBuilder.withMetaScore(Integer.valueOf(metaScoreSpan.text().trim()));
            }
        }

        Element ratingValueE = itemDoc.getElementsByAttributeValue("itemprop", "ratingValue").first();
        if (ratingValueE != null) {
            movieBuilder.withRatingAvg(Double.valueOf(ratingValueE.text().trim()));
        }

        Element ratingCountE = itemDoc.getElementsByAttributeValue("itemprop", "ratingCount").first();
        if (ratingCountE != null) {
            String ratingCountStr = ratingCountE.text().trim().replaceAll("[\\,]", "");
            movieBuilder.withRatingCount(Long.valueOf(ratingCountStr));
        }
        builder.withMovie(movieBuilder.build());
    }


    private void setGenres(List<GenreDto> genres) {
        Element storyLineE = itemDoc.getElementById("titleStoryLine");
        if (storyLineE != null) {
            Elements els = storyLineE.getElementsContainingOwnText("Genres");
            if (els != null) {
                Element genresH4 = els.first();
                if (genresH4 != null) {
                    Elements genresA = genresH4.parent().getElementsByTag("a");
                    if (genresA != null) {
                        Set<GenreDto> genresSet = genresA.stream()
                                .map(g -> genres.stream().filter(g2 -> g2.getName().equalsIgnoreCase(g.text().trim())).findFirst())
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.toSet());
                        if (genresSet != null) {
                            builder.withGenres(genresSet);
                        }
                    }
                }
            }
        }
    }


    private void downloadProfileImg(String profileImg, String movieId) {
        try {
            DownloadUtil.download(profileImg, movieId);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void setReleaseInfo() {
/*        Element releaseDatesTableE = releaseDoc.selectFirst("table.release-dates-table-test-only");
        if (releaseDatesTableE != null) {
            Elements releaseDateItems = releaseDatesTableE.select("tr.release-date-item");
            if (releaseDateItems != null && releaseDateItems.size() > 0) {
                Set<ReleaseInfo> releaseInfos = new HashSet<>();
                for (Element releaseDateItem : releaseDateItems) {
                    ReleaseInfo releaseInfo = new ReleaseInfo();
                    Element countryE = releaseDateItem.selectFirst("td.release-date-item__country-name a");
                    Element releaseDateE = releaseDateItem.selectFirst("td.release-date-item__date");
                    Element releaseAttrsE = releaseDateItem.selectFirst("td.release-date-item__attributes");
                    if (countryE != null) {
                        releaseInfo.setCountryName(countryE.text());
                        String countryHref = countryE.attr("href");
                        Matcher matcher = countryCodePattern.matcher(countryHref);
                        if (matcher.find()) {
                            String countryCode = matcher.group("countryCode");
                            if (countryCode != null) {
                                releaseInfo.setCountryCode(countryCode);
                            }
                        }
                    }
                    if (releaseDateE != null) {
                        String releaseDateRaw = releaseDateE.text().trim();
                        Matcher matcher = releaseDatePattern.matcher(releaseDateRaw);
                        Date releaseDate = null;
                        if (matcher.find()) {
                            Integer month = MonthUtil.getMonthNumber(matcher.group("month"));
                            releaseDate = DatesUtil.strToDate(String.format("%s-%s-%s", matcher.group("year"), month, matcher.group("day")));
                        } else {
                            matcher = yearMonthReleaseDatePattern.matcher(releaseDateRaw);
                            if (matcher.find()) {
                                Integer month = MonthUtil.getMonthNumber(matcher.group("month"));
                                releaseDate = DatesUtil.strToDate(String.format("%s-%s-%s", matcher.group("year"), month, "01"));
                            } else {
                                matcher = yearReleaseDatePattern.matcher(releaseDateRaw);
                                if (matcher.find()) {
                                    releaseDate = DatesUtil.strToDate(String.format("%s-%s-%s", matcher.group("year"), "01", "01"));
                                }
                            }
                        }
                        if (releaseDate == null) {
                            continue;
                        }
                        releaseInfo.setReleaseDate(releaseDate);
                    }
                    if (releaseAttrsE != null) {
                        releaseInfo.setNote(releaseAttrsE.text().trim());
                    }
                    releaseInfos.add(releaseInfo);
                }
                builder.releaseInfos(releaseInfos);
            }
        }*/
    }

    private void setWriters() {
/*        Element writingCreditsE = castAndCrewDoc.getElementsContainingOwnText("Writing Credits").first();
        if (writingCreditsE != null) {
            Element writingCreditsTableE = writingCreditsE.nextElementSibling();
            Elements writersRows = writingCreditsTableE.select("tbody tr");
            Map<Writer, String> writersCredits = new HashMap<>();
            for (Element writerRow : writersRows) {
                Element writerTd = writerRow.selectFirst("td.name");
                if (writerTd != null) {
                    Element writerA = writerTd.selectFirst("a");
                    if (writerA != null) {
                        Matcher matcher = profileIdPattern.matcher(writerA.attr("href"));
                        if (matcher.find()) {
                            String writerId = matcher.group("profileId");
                            String writerFullName = writerTd.text();
                            String credit = "";
                            Element creditTd = writerRow.selectFirst("td.credit");
                            if (creditTd != null) {
                                credit = creditTd.text().trim();
                            }
                            writersCredits.put(new Writer(writerId, writerFullName), credit);
                        }
                    }
                }

            }
            builder.writersCredits(writersCredits);
        }*/
    }

    private void setDirectors() {
/*        Element directorsCreditE = castAndCrewDoc.getElementsContainingOwnText("Directed by").first();
        if (directorsCreditE != null) {
            Element directorsCreditsTableE = directorsCreditE.nextElementSibling();
            Elements directorsRow = directorsCreditsTableE.select("tbody tr");
            Set<Director> directors = new HashSet<>();
            for (Element directorRow : directorsRow) {
                Element directorTd = directorRow.selectFirst("td.name");
                Element directorA = directorTd.selectFirst("a");
                if (directorA != null) {
                    Matcher matcher = profileIdPattern.matcher(directorA.attr("href"));
                    if (matcher.find()) {
                        String directorId = matcher.group("profileId");
                        String directorFullName = directorTd.text();
                        directors.add(new Director(directorId, directorFullName));
                    }
                }
            }
            builder.directors(directors);
        }*/
    }

    private void setCast() {
 /*       Element castList = castAndCrewDoc.selectFirst("table.cast_list");
        if (castList != null) {
            Elements actorsE = castList.select("tr.odd,tr.even");
            if (actorsE != null) {
                Map<Actor, Character> actorsCharacters = new HashMap<>();
                for (Element actorE : actorsE) {
                    Elements tds = actorE.select("td");
                    Actor actor = null;
                    Character character = new Character();
                    if (tds != null) {
                        Element actorNameTd = tds.get(1);
                        if (actorNameTd != null) {
                            Element actorNameA = actorNameTd.selectFirst("a");
                            if (actorNameA != null) {
                                String actorHref = actorNameA.attr("href");
                                Matcher matcher = profileIdPattern.matcher(actorHref);
                                if (matcher.find()) {
                                    String profileId = matcher.group("profileId");
                                    actor = new Actor(profileId, actorNameA.text().trim());
                                }
                            }
                        }
                        if (actor == null) {
                            continue;
                        }
                        Element characterTd = actorE.selectFirst("td.character");
                        if (characterTd != null) {
                            String characterName = characterTd.text();
                            character.setName(characterName.length() <= 120 ? characterName : characterName.substring(0, 119));
                            Element characterA = characterTd.selectFirst("a");
                            if (characterA != null) {
                                String characterHref = characterA.attr("href");
                                Matcher matcher = characterIdPattern.matcher(characterHref);
                                if (matcher.find()) {
                                    String characterId = matcher.group("characterId");
                                    character.setImdbId(characterId);
                                }

                            }
                        } else {
                            continue;
                        }
                    }
                    actorsCharacters.put(actor, character);
                }
                builder
                        .actorsCharacters(actorsCharacters);
            }
        }*/
    }

}

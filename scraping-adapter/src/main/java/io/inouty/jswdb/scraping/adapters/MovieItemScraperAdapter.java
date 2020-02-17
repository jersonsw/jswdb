package io.inouty.jswdb.scraping.adapters;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.exceptions.ConnectionFailedException;
import io.inouty.jswdb.core.ports.output.MovieItemScraperPort;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Time;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MovieItemScraperAdapter implements MovieItemScraperPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieItemScraperAdapter.class);
    private final String movieId;
    private final Document document;
    private final Map<String, String> urlsTemplates;
    private final Map<String, Pattern> patterns;

    public MovieItemScraperAdapter(String movieId, Map<String, String> urlsTemplates, Map<String, Pattern> patterns) {
        this.movieId = movieId;
        this.urlsTemplates = urlsTemplates;
        this.patterns = patterns;
        this.document = connectAndGetDocument();
    }

    private Document connectAndGetDocument() {
        if (!urlsTemplates.containsKey("movieUrlTemplate")) {
            throw new IllegalArgumentException("A template url with the key \"movieUrlTemplate\" must be present in the Map urlsTemplates");
        }
        final String movieUrl = String.format(urlsTemplates.get("movieUrlTemplate"), movieId);
        try {
            return Jsoup
                    .connect(movieUrl)
                    .maxBodySize(1024 * 1024 * 5)
                    .get();
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error trying to connect to: {}", movieUrl);
            }
            throw new ConnectionFailedException("Error trying to connect to the following page: " + movieUrl);
        }
    }


    @Override
    public Optional<Movie> extractBaseInfo() {
        final Movie.Builder builder = Movie.builder();
        builder.withId(movieId);
        Element slateWrapper = document.selectFirst("#title-overview-widget .slate_wrapper");
        if (slateWrapper != null) {
            Element slateButtonE = slateWrapper.selectFirst(".slate a.slate_button");
            if (slateButtonE != null) {
                builder.withTrailerUrl(slateButtonE.attr("href"));
            }
            Element posterImgE = slateWrapper.selectFirst(".poster img");
            if (posterImgE != null) {
                final String movieImgUrl = posterImgE.attr("src");
                if (movieImgUrl != null && !movieImgUrl.trim().isEmpty()) {
                    String[] urlParts = movieImgUrl.split("._V1_");
                    if (urlParts.length > 0) {
                        builder.withProfileImg(urlParts[0]);
                    } else {
                        builder.withProfileImg(movieImgUrl);
                    }
                }
                //this.downloadProfileImg(movieImgUrl, movieId);
            }
        }
        Element titleYearE = document.getElementById("titleYear");
        if (titleYearE != null) {
            Element titleYearA = titleYearE.selectFirst("a");
            if (titleYearA != null) {
                String itemYearStr = titleYearA.text().trim();
                if (!itemYearStr.isEmpty()) {
                    builder.withYear(Integer.valueOf(itemYearStr));
                }
            }
        }

        Element titleWrapper = document.selectFirst(".title_wrapper");
        if (titleWrapper != null) {
            Element durationE = titleWrapper.selectFirst("time[datetime]");
            if (durationE != null) {
                if (!patterns.containsKey("durationPattern")) {
                    throw new IllegalArgumentException("A pattern with the key \"durationPattern\" must be present in the Map of patterns");
                }
                Matcher matcher = patterns.get("durationPattern").matcher(durationE.text().trim());
                if (matcher.find()) {
                    Integer minutes = Integer.valueOf(matcher.group("amount"));
                    String durationStr = (minutes / 60) + ":" + (minutes % 60) + ":00";
                    builder.withDuration(Time.valueOf(durationStr));
                }
            }
            Element titleH1 = titleWrapper.getElementsByTag("h1").first();
            if (titleH1 != null) {
                if (titleYearE != null) {
                    titleYearE.remove();
                }
                builder.withTitle(titleH1.text());
            }
        }

        Element plotSummaryWrapper = document.selectFirst(".plot_summary_wrapper");
        if (plotSummaryWrapper != null) {
            Element plotSummary = plotSummaryWrapper.selectFirst(".plot_summary");
            if (plotSummary != null) {
                builder.withSummary(plotSummary.text());
            }
        }

        Element metaScoreE = document.selectFirst(".metacriticScore");
        if (metaScoreE != null) {
            Element metaScoreSpan = metaScoreE.getElementsByTag("span").first();
            if (metaScoreSpan != null) {
                builder.withMetaScore(Integer.valueOf(metaScoreSpan.text().trim()));
            }
        }

        Element ratingValueE = document.getElementsByAttributeValue("itemprop", "ratingValue").first();
        if (ratingValueE != null) {
            builder.withRatingAvg(Double.valueOf(ratingValueE.text().trim()));
        }

        Element ratingCountE = document.getElementsByAttributeValue("itemprop", "ratingCount").first();
        if (ratingCountE != null) {
            String ratingCountStr = ratingCountE.text().trim().replaceAll("[\\,]", "");
            builder.withRatingCount(Long.valueOf(ratingCountStr));
        }

        Movie movie = builder.build();

        return Optional.of(movie);
    }

    @Override
    public Set<Genre> extractGenres(Set<Genre> genresToFilter) {
        Set<Genre> genresSet = Collections.emptySet();
        Element storyLineE = document.getElementById("titleStoryLine");
        if (storyLineE != null) {
            Elements els = storyLineE.getElementsContainingOwnText("Genres");
            if (els != null) {
                Element genresH4 = els.first();
                if (genresH4 != null) {
                    Elements genresA = genresH4.parent().getElementsByTag("a");
                    if (genresA != null) {
                        genresSet = genresA.stream()
                                .map(g -> genresToFilter.stream().filter(g2 -> g2.getName().equalsIgnoreCase(g.text().trim())).findFirst())
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.toSet());
                    }
                }
            }
        }
        return genresSet;
    }
}

package io.inouty.jswdb.scraping;


import io.inouty.jswdb.core.domain.SearchPagination;
import io.inouty.jswdb.core.exceptions.ConnectionFailedException;
import io.inouty.jswdb.core.ports.scrapers.MoviesSearchScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MoviesSearchScraperImpl implements MoviesSearchScraper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoviesSearchScraperImpl.class);
    private final Document document;
    private final String searchUrl;
    private final Pattern paginationPattern;

    public MoviesSearchScraperImpl(String searchUrl, Pattern paginationPattern) {
        this.searchUrl = searchUrl;
        this.paginationPattern = paginationPattern;
        this.document = connectAndGetDocument();
    }

    private Document connectAndGetDocument() {
        try {
            return Jsoup
                    .connect(searchUrl)
                    .maxBodySize(0)
                    .get();
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error trying to connect to: {}", searchUrl);
            }
            throw new ConnectionFailedException("Error trying to connect to the following page: " + searchUrl);
        }
    }


    @Override
    public Set<String> extractIds() {
        Set<String> moviesIds = Collections.emptySet();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Connecting to: {}", searchUrl);
            LOGGER.debug("Connected to: {}", searchUrl);
        }
        final Elements imgsE = document.select(".lister-item-image a img");
        if (imgsE != null) {
            moviesIds = imgsE.stream().map(img -> img.attr("data-tconst")).filter(id -> id != null).collect(Collectors.toSet());
        }
        return moviesIds;
    }


    private Optional<String> getNextPageUrl() {
        final Element nextButton = document.selectFirst("a.lister-page-next.next-page");
        if (nextButton != null) {
            final String url = nextButton.attr("abs:href");
            if (!url.trim().isEmpty()) {
                return Optional.of(url);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<SearchPagination> extractPagination() {
        final String paginationInfo = document.select("div.desc span").first().text();
        final Matcher matcher = paginationPattern.matcher(paginationInfo);
        Optional<String> nexPageUrl = getNextPageUrl();
        if (matcher.find()) {
            final Integer start = Integer.valueOf(matcher.group("recordStart").replaceAll("[\\,\\.]+", ""));
            final Integer end = Integer.valueOf(matcher.group("recordEnd").replaceAll("[\\,\\.]+", ""));
            final Integer count = Integer.valueOf(matcher.group("recordsCount").replaceAll("[\\,\\.]+", ""));
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Reading from {} to {} of {}", start, end, count);
            }
            return Optional.of(
                    SearchPagination
                            .builder()
                            .withStartRecord(start)
                            .withEndRecord(end)
                            .withRecordsCount(count)
                            .withNextPageUrl(nexPageUrl).build()
            );
        }
        return Optional.empty();
    }


}

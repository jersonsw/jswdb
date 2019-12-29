package io.inouty.jswdb.batch.processors;

import io.inouty.jswdb.core.domain.GenreDto;
import io.inouty.jswdb.core.domain.MovieDto;
import io.inouty.jswdb.core.ports.scrapers.MovieItemScraper;
import io.inouty.jswdb.core.usecases.GetAllGenres;
import io.inouty.jswdb.core.usecases.ScrapeMovieBaseInfo;
import io.inouty.jswdb.scraping.MovieItemScraperImpl;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class ImdbItemsProcessor implements ItemProcessor<String, MovieDto> {

    private final GetAllGenres getAllGenres;

    private List<GenreDto> genres = new ArrayList<>();

    @Value("${imdb.urls.item-details}")
    private String movieUrlTemplate;

    @Value("#{T(java.util.regex.Pattern).compile('${imdb.patterns.duration}')}")
    private Pattern durationPattern;

    Map<String, String> urlsTemplates;

    Map<String, Pattern> patterns;

    public ImdbItemsProcessor(GetAllGenres getAllGenres) {
        this.getAllGenres = getAllGenres;
    }

    @BeforeStep()
    public void beforeStep() {
        this.genres = this.getAllGenres.execute();
        this.urlsTemplates = new HashMap<String, String>() {{
            put("movieUrlTemplate", movieUrlTemplate);
        }};
        this.patterns = new HashMap<String, Pattern>() {{
            put("durationPattern", durationPattern);
        }};
    }

    @Override
    public synchronized MovieDto process(String movieId) {
        final MovieItemScraper scraper = new MovieItemScraperImpl(movieId, urlsTemplates, patterns);
        final ScrapeMovieBaseInfo scrapeMovies = new ScrapeMovieBaseInfo(scraper);
        final MovieDto movie = scrapeMovies.execute();
        return movie;
    }
}

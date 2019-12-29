package io.inouty.jswdb.batch.processors;

import io.inouty.jswdb.core.entities.movie.GenreDto;
import io.inouty.jswdb.core.entities.movie.MovieDto;
import io.inouty.jswdb.core.usecases.GetAllGenresUseCase;
import io.inouty.jswdb.core.usecases.ScrapeMovieBaseInfoUseCase;
import io.inouty.jswdb.core.usecases.ports.scrapers.MovieItemScraper;
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

    private final GetAllGenresUseCase getAllGenresUseCase;

    private List<GenreDto> genres = new ArrayList<>();

    @Value("${imdb.urls.item-details}")
    private String movieUrlTemplate;

    @Value("#{T(java.util.regex.Pattern).compile('${imdb.patterns.duration}')}")
    private Pattern durationPattern;

    Map<String, String> urlsTemplates;

    Map<String, Pattern> patterns;

    public ImdbItemsProcessor(GetAllGenresUseCase getAllGenresUseCase) {
        this.getAllGenresUseCase = getAllGenresUseCase;
    }

    @BeforeStep()
    public void beforeStep() {
        this.genres = this.getAllGenresUseCase.execute();
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
        final ScrapeMovieBaseInfoUseCase scrapeMovies = new ScrapeMovieBaseInfoUseCase(scraper);
        final MovieDto movie = scrapeMovies.execute();
        return movie;
    }
}

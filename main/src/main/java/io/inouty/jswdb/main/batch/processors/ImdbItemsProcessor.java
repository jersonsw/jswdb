package io.inouty.jswdb.main.batch.processors;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.ports.input.FindGenresPort;
import io.inouty.jswdb.core.ports.input.ScrapeMovieItemPort;
import io.inouty.jswdb.core.ports.output.MovieItemScraperPort;
import io.inouty.jswdb.core.ports.usecases.ScrapeMovieItemUseCase;
import io.inouty.jswdb.scraping.adapters.MovieItemScraperAdapter;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class ImdbItemsProcessor implements ItemProcessor<String, Movie> {

    private final FindGenresPort findGenresPort;

    private Set<Genre> genres = new HashSet<Genre>();

    @Value("${imdb.urls.item-details}")
    private String movieUrlTemplate;

    @Value("#{T(java.util.regex.Pattern).compile('${imdb.patterns.duration}')}")
    private Pattern durationPattern;

    private Map<String, String> urlsTemplates;

    private Map<String, Pattern> patterns;

    public ImdbItemsProcessor(FindGenresPort findGenresPort) {
        this.findGenresPort = findGenresPort;
    }

    @BeforeStep()
    public void beforeStep() {
        this.genres = this.findGenresPort.findAll();
        this.urlsTemplates = new HashMap<String,String>() {{
            put("movieUrlTemplate", movieUrlTemplate);
        }};
        this.patterns = new HashMap<String, Pattern>() {{
            put("durationPattern", durationPattern);
        }};
    }

    @Override
    public synchronized Movie process(String movieId) {
        final MovieItemScraperPort scraper = new MovieItemScraperAdapter(movieId, urlsTemplates, patterns);
        final ScrapeMovieItemPort scrapeMovies = new ScrapeMovieItemUseCase(scraper);
        final Movie movie = scrapeMovies.extractBaseInfo();
        return movie;
    }
}

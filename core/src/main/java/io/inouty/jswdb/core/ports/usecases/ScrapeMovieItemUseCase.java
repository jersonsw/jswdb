package io.inouty.jswdb.core.ports.usecases;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.ports.input.ScrapeMovieItemPort;
import io.inouty.jswdb.core.ports.output.MovieItemScraperPort;

import java.util.Collections;
import java.util.Set;

public class ScrapeMovieItemUseCase implements ScrapeMovieItemPort {

    private final MovieItemScraperPort scraper;

    public ScrapeMovieItemUseCase(MovieItemScraperPort scraper) {
        this.scraper = scraper;
    }

    @Override
    public Movie extractBaseInfo() {
        return this.scraper.extractBaseInfo().get();
    }

    @Override
    public Set<Genre> extractGenres(Set<Genre> genresToFilter) {
        final Set<Genre> genres = this.scraper.extractGenres(genresToFilter);
        if (genres != null && !genres.isEmpty()) {
            return genres;
        }
        return Collections.emptySet();
    }
}

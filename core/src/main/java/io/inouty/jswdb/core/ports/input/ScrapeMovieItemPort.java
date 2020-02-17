package io.inouty.jswdb.core.ports.input;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.domain.movie.Movie;

import java.util.Set;

public interface ScrapeMovieItemPort {
    Movie extractBaseInfo();
    Set<Genre> extractGenres(Set<Genre> genresToFilter);
}

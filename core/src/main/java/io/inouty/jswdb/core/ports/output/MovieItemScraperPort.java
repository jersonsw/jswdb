package io.inouty.jswdb.core.ports.output;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.domain.movie.Movie;

import java.util.Optional;
import java.util.Set;

public interface MovieItemScraperPort {
    Optional<Movie> extractBaseInfo();
    Set<Genre> extractGenres(Set<Genre> genresToFilter);
}

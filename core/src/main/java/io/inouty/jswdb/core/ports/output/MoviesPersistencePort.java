package io.inouty.jswdb.core.ports.output;

import io.inouty.jswdb.core.domain.movie.Movie;

import java.util.Set;

public interface MoviesPersistencePort {
    Set<Movie> saveAll(Set<Movie> movies);
    Movie save(Movie movie);
}

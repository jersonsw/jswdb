package io.inouty.jswdb.core.ports.input;

import io.inouty.jswdb.core.domain.movie.Movie;

import java.util.Set;

public interface CreateMoviesPort {
    Movie save(Movie movies);
    Set<Movie> saveAll(Set<Movie> movies);
}

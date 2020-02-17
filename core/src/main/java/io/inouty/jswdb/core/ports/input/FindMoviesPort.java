package io.inouty.jswdb.core.ports.input;

import io.inouty.jswdb.core.domain.movie.Movie;

import java.util.Set;

public interface FindMoviesPort {
    Movie findOne();
    Set<Movie> findAll();
}

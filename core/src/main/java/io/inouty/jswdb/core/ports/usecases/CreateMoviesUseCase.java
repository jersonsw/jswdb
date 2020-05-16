package io.inouty.jswdb.core.ports.usecases;


import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.ports.input.CreateMoviesPort;
import io.inouty.jswdb.core.ports.output.MoviesPersistencePort;

import java.util.Set;

public class CreateMoviesUseCase implements CreateMoviesPort {

    private final MoviesPersistencePort repository;

    public CreateMoviesUseCase(MoviesPersistencePort repository) {
        this.repository = repository;
    }

    @Override
    public Movie save(Movie movie) {
        return this.repository.save(movie);
    }

    @Override
    public Set<Movie> saveAll(Set<Movie> movies) {
        return this.repository.saveAll(movies);
    }


}

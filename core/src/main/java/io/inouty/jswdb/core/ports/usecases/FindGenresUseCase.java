package io.inouty.jswdb.core.ports.usecases;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.exceptions.GenreNotFoundException;
import io.inouty.jswdb.core.ports.input.FindGenresPort;
import io.inouty.jswdb.core.ports.output.GenresPersistencePort;

import java.util.Set;

public class FindGenresUseCase implements FindGenresPort {

    private final GenresPersistencePort repository;

    public FindGenresUseCase(GenresPersistencePort repository) {
        this.repository = repository;
    }

    @Override
    public Genre findOne(Long id) {
        return this.repository.findOne(id)
                .orElseThrow(GenreNotFoundException.exceptionSupplier("Genre not found"));
    }

    @Override
    public Set<Genre> findAll() {
        final Set<Genre> genres = this.repository.findAll();
        if (genres.size() > 0) {
            return genres;
        }
        throw new GenreNotFoundException("No genres found");
    }
}

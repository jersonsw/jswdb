package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.movie.GenreDto;
import io.inouty.jswdb.core.usecases.ports.repositories.GenresRepository;
import io.inouty.jswdb.core.usecases.contracts.OutputUseCase;

import java.util.List;

public class GetAllGenresUseCase implements OutputUseCase<List<GenreDto>> {

    private GenresRepository repository;

    public GetAllGenresUseCase(GenresRepository repository) {
        this.repository = repository;
    }

    @Override()
    public List<GenreDto> execute() {
        return this.repository.findAll();
    }
}

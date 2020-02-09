package io.inouty.jswdb.usecases;

import io.inouty.jswdb.domain.movie.GenreDto;
import io.inouty.jswdb.usecases.contracts.OutputUseCase;
import io.inouty.jswdb.usecases.exceptions.NoGenresFoundException;
import io.inouty.jswdb.usecases.ports.repositories.GenresRepository;

import java.util.List;

public class GetAllGenresUseCase implements OutputUseCase<List<GenreDto>> {

    private GenresRepository repository;

    public GetAllGenresUseCase(GenresRepository repository) {
        this.repository = repository;
    }

    @Override()
    public List<GenreDto> execute() {
        final List<GenreDto> genres = this.repository.findAll();
        if (genres.size() > 0) {
            return genres;
        }
        throw new NoGenresFoundException("No genres found");
    }
}

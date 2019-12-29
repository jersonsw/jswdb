package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.movie.MovieDto;
import io.inouty.jswdb.core.usecases.ports.repositories.MoviesRepository;
import io.inouty.jswdb.core.usecases.contracts.UseCase;

import java.util.List;

public class CreateMoviesUseCase implements UseCase<List<MovieDto>, List<MovieDto>> {

    private MoviesRepository repository;

    public CreateMoviesUseCase(MoviesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MovieDto> execute(List<MovieDto> movies) {
        return this.repository.saveAll(movies);
    }

}

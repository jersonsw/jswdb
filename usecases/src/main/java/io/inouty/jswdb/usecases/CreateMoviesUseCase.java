package io.inouty.jswdb.usecases;


import io.inouty.jswdb.domain.movie.MovieDto;
import io.inouty.jswdb.usecases.contracts.UseCase;
import io.inouty.jswdb.usecases.ports.repositories.MoviesRepository;

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

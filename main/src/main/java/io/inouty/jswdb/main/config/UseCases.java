package io.inouty.jswdb.main.config;

import io.inouty.jswdb.usecases.CreateMoviesUseCase;
import io.inouty.jswdb.usecases.GetAllGenresUseCase;
import io.inouty.jswdb.usecases.ports.repositories.GenresRepository;
import io.inouty.jswdb.usecases.ports.repositories.MoviesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public final class UseCases {

    private final MoviesRepository moviesRepository;
    private final GenresRepository genresRepository;

    public UseCases(MoviesRepository moviesRepository, GenresRepository genresRepository) {
        this.moviesRepository = moviesRepository;
        this.genresRepository = genresRepository;
    }

    @Bean
    public CreateMoviesUseCase createMovies() {
        return new CreateMoviesUseCase(moviesRepository);
    }

    @Bean
    public GetAllGenresUseCase getAllGenres() {
        return new GetAllGenresUseCase(genresRepository);
    }

}

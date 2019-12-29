package io.inouty.jswdb.batch.configs;

import io.inouty.jswdb.core.ports.repositories.GenresRepository;
import io.inouty.jswdb.core.ports.repositories.MoviesRepository;
import io.inouty.jswdb.core.usecases.CreateMovies;
import io.inouty.jswdb.core.usecases.GetAllGenres;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UseCases {


    private MoviesRepository moviesRepository;
    private GenresRepository genresRepository;

    public UseCases(MoviesRepository moviesRepository, GenresRepository genresRepository) {
        this.moviesRepository = moviesRepository;
        this.genresRepository = genresRepository;
    }

    @Bean
    public CreateMovies createMovies() {
        return new CreateMovies(moviesRepository);
    }

    @Bean
    public GetAllGenres getAllGenres() {
        return new GetAllGenres(genresRepository);
    }

}

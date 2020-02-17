package io.inouty.jswdb.main.config;

import io.inouty.jswdb.core.ports.input.CreateMoviesPort;
import io.inouty.jswdb.core.ports.input.FindGenresPort;
import io.inouty.jswdb.core.ports.output.GenresPersistencePort;
import io.inouty.jswdb.core.ports.output.MoviesPersistencePort;
import io.inouty.jswdb.core.ports.usecases.CreateMoviesUseCase;
import io.inouty.jswdb.core.ports.usecases.FindGenresUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public final class UseCases {

    private final GenresPersistencePort genresPersistencePort;
    private final MoviesPersistencePort moviesPersistencePort;

    public UseCases(MoviesPersistencePort moviesPersistencePort, GenresPersistencePort genresPersistencePort) {
        this.moviesPersistencePort = moviesPersistencePort;
        this.genresPersistencePort = genresPersistencePort;
    }

    @Bean
    public CreateMoviesPort createMoviesPort() {
        return new CreateMoviesUseCase(moviesPersistencePort);
    }

    @Bean
    public FindGenresPort findGenresPort() {
        return new FindGenresUseCase(genresPersistencePort);
    }

}

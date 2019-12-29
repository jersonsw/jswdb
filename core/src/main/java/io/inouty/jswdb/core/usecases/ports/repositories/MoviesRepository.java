package io.inouty.jswdb.core.usecases.ports.repositories;

import io.inouty.jswdb.core.entities.movie.MovieDto;
import java.util.List;

public interface MoviesRepository {
    List<MovieDto> saveAll(List<MovieDto> movies);
    MovieDto save(MovieDto movie);
}

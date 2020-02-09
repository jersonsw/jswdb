package io.inouty.jswdb.usecases.ports.repositories;

import io.inouty.jswdb.domain.movie.MovieDto;
import java.util.List;

public interface MoviesRepository {
    List<MovieDto> saveAll(List<MovieDto> movies);
    MovieDto save(MovieDto movie);
}

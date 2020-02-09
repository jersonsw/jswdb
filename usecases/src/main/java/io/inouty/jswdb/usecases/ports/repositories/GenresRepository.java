package io.inouty.jswdb.usecases.ports.repositories;


import io.inouty.jswdb.domain.movie.GenreDto;

import java.util.List;

public interface GenresRepository {

    List<GenreDto> findAll();

}

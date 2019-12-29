package io.inouty.jswdb.core.usecases.ports.repositories;

import io.inouty.jswdb.core.entities.movie.GenreDto;

import java.util.List;

public interface GenresRepository {

    List<GenreDto> findAll();

}

package io.inouty.jswdb.core.ports.input;

import io.inouty.jswdb.core.domain.movie.Genre;

import java.util.Set;

public interface FindGenresPort {
    Genre findOne(Long id);
    Set<Genre> findAll();
}

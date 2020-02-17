package io.inouty.jswdb.core.ports.output;


import io.inouty.jswdb.core.domain.movie.Genre;

import java.util.Optional;
import java.util.Set;

public interface GenresPersistencePort {
    Optional<Genre> findOne(Long id);
    Set<Genre> findAll();
}

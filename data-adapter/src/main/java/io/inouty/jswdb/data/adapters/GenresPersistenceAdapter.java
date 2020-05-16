package io.inouty.jswdb.data.adapters;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.ports.output.GenresPersistencePort;
import io.inouty.jswdb.data.entities.GenreEntity;
import io.inouty.jswdb.data.jpa.GenresRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository()
public class GenresPersistenceAdapter implements GenresPersistencePort {

    private final GenresRepository repository;

    public GenresPersistenceAdapter(GenresRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Genre> findOne(Long id) {
        return this.repository.findById(id).map(g -> g.to());
    }

    @Override
    public Set<Genre> findAll() {
        List<GenreEntity> genres = this.repository.findAll();
        if (genres.isEmpty()) {
            return Collections.emptySet();
        }
        return genres.stream().map(e -> e.to()).collect(Collectors.toSet());
    }
}

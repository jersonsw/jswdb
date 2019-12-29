package io.inouty.jswdb.data.repositories;

import io.inouty.jswdb.core.domain.GenreDto;
import io.inouty.jswdb.core.ports.repositories.GenresRepository;
import io.inouty.jswdb.data.EntityDtoMapper;
import io.inouty.jswdb.data.entities.Genre;
import io.inouty.jswdb.data.repositories.jpa.GenresJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GenresRepositoryImpl implements GenresRepository {

    private GenresJpaRepository jpaRepository;

    public GenresRepositoryImpl(GenresJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<GenreDto> findAll() {
        List<Genre> genres = this.jpaRepository.findAll();
        if (genres.isEmpty()) {
            return Collections.emptyList();
        }
        return genres.stream().map(EntityDtoMapper::fromGenre).collect(Collectors.toList());
    }
}

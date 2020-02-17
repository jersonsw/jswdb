package io.inouty.jswdb.data.jpa;

import io.inouty.jswdb.data.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<GenreEntity, Long> {}

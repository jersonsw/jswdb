package io.inouty.jswdb.data.repositories.jpa;

import io.inouty.jswdb.data.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresJpaRepository extends JpaRepository<Genre,Long> {}
package io.inouty.jswdb.data.jpa;

import io.inouty.jswdb.data.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<MovieEntity, String> {
}

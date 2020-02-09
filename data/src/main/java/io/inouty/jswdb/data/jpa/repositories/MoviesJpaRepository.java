package io.inouty.jswdb.data.jpa.repositories;

import io.inouty.jswdb.data.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesJpaRepository extends JpaRepository<Movie, String> {
}

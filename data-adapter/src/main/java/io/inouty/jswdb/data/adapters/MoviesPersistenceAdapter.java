package io.inouty.jswdb.data.adapters;

import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.ports.output.MoviesPersistencePort;
import io.inouty.jswdb.data.entities.MovieEntity;
import io.inouty.jswdb.data.jpa.MoviesRepository;
import io.inouty.jswdb.data.mappers.EntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MoviesPersistenceAdapter implements MoviesPersistencePort {

    private final MoviesRepository repository;

    public MoviesPersistenceAdapter(MoviesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Movie> saveAll(Set<Movie> movies) {
        if (movies.size() > 0) {
            Set<MovieEntity> movieEntityList = movies.stream().map(EntityMapper::from).collect(Collectors.toSet());
            this.repository.saveAll(movieEntityList);
            return movieEntityList.stream().map(e->e.to()).collect(Collectors.toSet());
        }
        throw new IllegalArgumentException("The movies list shouldn't be empty");
    }

    @Override
    public Movie save(Movie movieDto) {
        MovieEntity movieEntity = EntityMapper.from(movieDto);
        return movieEntity.to();
    }
}

package io.inouty.jswdb.data.repositories;

import io.inouty.jswdb.core.domain.MovieDto;
import io.inouty.jswdb.core.ports.repositories.MoviesRepository;
import io.inouty.jswdb.data.EntityDtoMapper;
import io.inouty.jswdb.data.entities.Movie;
import io.inouty.jswdb.data.repositories.jpa.MoviesJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MoviesRepositoryImpl implements MoviesRepository {

    private MoviesJpaRepository jpaRepository;

    public MoviesRepositoryImpl(MoviesJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<MovieDto> saveAll(List<MovieDto> movies) {
        if (movies.size() > 0) {
            List<Movie> movieList = movies.stream().map(EntityDtoMapper::toMovie).collect(Collectors.toList());
            this.jpaRepository.saveAll(movieList);
            return movieList.stream().map(EntityDtoMapper::fromMovie).collect(Collectors.toList());
        }
        throw new IllegalArgumentException("The movies list shouldn't be empty");
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        Movie movie = EntityDtoMapper.toMovie(movieDto);
        return EntityDtoMapper.fromMovie(movie);
    }
}

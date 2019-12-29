package io.inouty.jswdb.core.usecases.ports.scrapers;

import io.inouty.jswdb.core.entities.movie.GenreDto;
import io.inouty.jswdb.core.entities.movie.MovieDto;
import java.util.Set;

public interface MovieItemScraper {
    MovieDto extractBaseInfo();
    Set<GenreDto> extractGenres(Set<GenreDto> genresToFilter);
}

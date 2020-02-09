package io.inouty.jswdb.usecases.ports.scrapers;

import io.inouty.jswdb.domain.movie.GenreDto;
import io.inouty.jswdb.domain.movie.MovieDto;

import java.util.Set;

public interface MovieItemScraper {
    MovieDto extractBaseInfo();
    Set<GenreDto> extractGenres(Set<GenreDto> genresToFilter);
}

package io.inouty.jswdb.usecases;

import io.inouty.jswdb.domain.movie.GenreDto;
import io.inouty.jswdb.usecases.contracts.UseCase;
import io.inouty.jswdb.usecases.exceptions.NoGenresFoundException;
import io.inouty.jswdb.usecases.ports.scrapers.MovieItemScraper;

import java.util.Set;

public class ScrapeMovieGenresUseCase implements UseCase<Set<GenreDto>, Set<GenreDto>> {

    private MovieItemScraper scraper;

    public ScrapeMovieGenresUseCase(MovieItemScraper scraper) {
        this.scraper = scraper;
    }

    @Override()
    public Set<GenreDto> execute(Set<GenreDto> genresToFilter) {
        final Set<GenreDto> genres = this.scraper.extractGenres(genresToFilter);
        if (genres != null && !genres.isEmpty()) {
            return genres;
        }
        throw new NoGenresFoundException("No genres found");
    }


}

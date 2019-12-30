package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.movie.GenreDto;
import io.inouty.jswdb.core.usecases.exceptions.NoMovieGenresFoundException;
import io.inouty.jswdb.core.usecases.ports.scrapers.MovieItemScraper;
import io.inouty.jswdb.core.usecases.contracts.UseCase;
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
        throw new NoMovieGenresFoundException("No genres found");
    }


}

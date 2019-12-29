package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.movie.MovieDto;
import io.inouty.jswdb.core.usecases.ports.scrapers.MovieItemScraper;
import io.inouty.jswdb.core.usecases.contracts.OutputUseCase;

public class ScrapeMovieBaseInfo implements OutputUseCase<MovieDto> {

    private MovieItemScraper scraper;

    public ScrapeMovieBaseInfo(MovieItemScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public MovieDto execute() {
        return this.scraper.extractBaseInfo();
    }
}

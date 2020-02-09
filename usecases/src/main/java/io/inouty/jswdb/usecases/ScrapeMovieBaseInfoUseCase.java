package io.inouty.jswdb.usecases;

import io.inouty.jswdb.domain.movie.MovieDto;
import io.inouty.jswdb.usecases.contracts.OutputUseCase;
import io.inouty.jswdb.usecases.ports.scrapers.MovieItemScraper;

public class ScrapeMovieBaseInfoUseCase implements OutputUseCase<MovieDto> {

    private MovieItemScraper scraper;

    public ScrapeMovieBaseInfoUseCase(MovieItemScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public MovieDto execute() {
        return this.scraper.extractBaseInfo();
    }
}

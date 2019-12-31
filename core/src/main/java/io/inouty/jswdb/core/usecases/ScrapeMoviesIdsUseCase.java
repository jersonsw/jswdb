package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.usecases.exceptions.NoMoviesIdsFoundException;
import io.inouty.jswdb.core.usecases.ports.scrapers.MoviesSearchScraper;
import io.inouty.jswdb.core.usecases.contracts.OutputUseCase;

import java.util.Set;

public class ScrapeMoviesIdsUseCase implements OutputUseCase<Set<String>> {

    private final MoviesSearchScraper scraper;

    public ScrapeMoviesIdsUseCase(MoviesSearchScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public Set<String> execute() {
        final Set<String> moviesIds = this.scraper.extractIds();
        if (!moviesIds.isEmpty()) {
            return moviesIds;
        }
        throw new NoMoviesIdsFoundException("No movies ids found");
    }

}

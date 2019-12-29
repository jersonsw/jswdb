package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.SearchPagination;
import io.inouty.jswdb.core.usecases.exceptions.PaginationInfoNotFoundException;
import io.inouty.jswdb.core.usecases.ports.scrapers.MoviesSearchScraper;
import io.inouty.jswdb.core.usecases.contracts.OutputUseCase;

import java.util.Optional;

public class ScrapeMoviesPagination implements OutputUseCase<SearchPagination> {

    private MoviesSearchScraper scraper;

    public ScrapeMoviesPagination(MoviesSearchScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public SearchPagination execute() {
        final Optional<SearchPagination> paginationInfo = this.scraper.extractPagination();
        if (paginationInfo.isPresent()) {
            return paginationInfo.get();
        }
        throw new PaginationInfoNotFoundException("No pagination information found");
    }
}

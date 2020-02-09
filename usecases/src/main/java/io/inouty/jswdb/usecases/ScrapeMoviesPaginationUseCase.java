package io.inouty.jswdb.usecases;

import io.inouty.jswdb.domain.SearchPagination;
import io.inouty.jswdb.usecases.contracts.OutputUseCase;
import io.inouty.jswdb.usecases.exceptions.PaginationInfoNotFoundException;
import io.inouty.jswdb.usecases.ports.scrapers.MoviesSearchScraper;

import java.util.Optional;

public class ScrapeMoviesPaginationUseCase implements OutputUseCase<SearchPagination> {

    private MoviesSearchScraper scraper;

    public ScrapeMoviesPaginationUseCase(MoviesSearchScraper scraper) {
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

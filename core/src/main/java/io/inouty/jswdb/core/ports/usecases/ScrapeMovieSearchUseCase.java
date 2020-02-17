package io.inouty.jswdb.core.ports.usecases;

import io.inouty.jswdb.core.domain.SearchPagination;
import io.inouty.jswdb.core.ports.input.ScrapeMovieSearchPort;
import io.inouty.jswdb.core.ports.output.MoviesSearchScraperPort;

import java.util.Collections;
import java.util.Set;

public class ScrapeMovieSearchUseCase implements ScrapeMovieSearchPort {

    private final MoviesSearchScraperPort scraper;

    public ScrapeMovieSearchUseCase(MoviesSearchScraperPort scraper) {
        this.scraper = scraper;
    }

    @Override
    public Set<String> extractMoviesIds() {
        final Set<String> moviesIds = this.scraper.extractMoviesIds();
        if (!moviesIds.isEmpty()) {
            return moviesIds;
        }
        return Collections.emptySet();
    }

    @Override
    public SearchPagination extractPaginationInfo() {
        return this.scraper.extractPaginationInfo().get();
    }
}

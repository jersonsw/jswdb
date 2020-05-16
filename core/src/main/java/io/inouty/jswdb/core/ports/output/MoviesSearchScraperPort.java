package io.inouty.jswdb.core.ports.output;

import io.inouty.jswdb.core.domain.SearchPagination;

import java.util.Optional;
import java.util.Set;

public interface MoviesSearchScraperPort {
    Set<String> extractMoviesIds();
    Optional<SearchPagination> extractPaginationInfo();
}

package io.inouty.jswdb.core.usecases.ports.scrapers;

import io.inouty.jswdb.core.entities.SearchPagination;
import java.util.Optional;
import java.util.Set;

public interface MoviesSearchScraper {
    Set<String> extractIds();
    Optional<SearchPagination> extractPagination();
}

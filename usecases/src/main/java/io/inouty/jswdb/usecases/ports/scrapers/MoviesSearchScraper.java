package io.inouty.jswdb.usecases.ports.scrapers;

import io.inouty.jswdb.domain.SearchPagination;

import java.util.Optional;
import java.util.Set;

public interface MoviesSearchScraper {
    Set<String> extractIds();
    Optional<SearchPagination> extractPagination();
}

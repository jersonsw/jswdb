package io.inouty.jswdb.core.ports.input;

import io.inouty.jswdb.core.domain.SearchPagination;
import java.util.Set;

public interface ScrapeMovieSearchPort {
    Set<String> extractMoviesIds();
    SearchPagination extractPaginationInfo();
}

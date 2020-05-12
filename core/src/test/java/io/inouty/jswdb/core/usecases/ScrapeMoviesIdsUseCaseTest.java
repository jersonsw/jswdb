package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.domain.SearchPagination;
import io.inouty.jswdb.core.ports.input.ScrapeMovieSearchPort;
import io.inouty.jswdb.core.ports.output.MoviesSearchScraperPort;
import io.inouty.jswdb.core.ports.usecases.ScrapeMovieSearchUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScrapeMoviesIdsUseCaseTest {

    private MoviesSearchScraperPort moviesSearchScraperPort;
    private ScrapeMovieSearchPort scrapeMovieSearchPort;
    private Set<String> moviesIds;
    private SearchPagination searchPagination;


    @BeforeEach
    void setUp() {
        moviesIds = new HashSet<String>() {{
            add("tt4154796");
            add("tt7286456");
            add("tt4154664");
            add("tt7131622");
            add("tt6320628");
            add("tt6146586");
            add("tt0448115");
            add("tt0437086");
            add("tt6823368");
            add("tt6857112");
        }};
        searchPagination = searchPagination
                .builder()
                .withStartRecord(0)
                .withEndRecord(200)
                .withRecordsCount(10751)
                .withNextPageUrl(Optional.of("http://search/title/?release_date=1970-12-31,2018-01-01&start=51&ref_=adv_nxt"))
                .build();
        moviesSearchScraperPort = mock(MoviesSearchScraperPort.class);
        scrapeMovieSearchPort = new ScrapeMovieSearchUseCase(moviesSearchScraperPort);
    }

    @AfterEach
    void tearDown() {
        moviesIds = null;
        moviesSearchScraperPort = null;
        scrapeMovieSearchPort = null;
    }

    @Test
    void extractMoviesIdsTest() {
        when(moviesSearchScraperPort.extractMoviesIds()).thenReturn(moviesIds);
        Set<String> result = scrapeMovieSearchPort.extractMoviesIds();
        assertThat(result, containsInAnyOrder(moviesIds.toArray()));
    }


    @Test
    void extractMovies() {
        when(moviesSearchScraperPort.extractPaginationInfo()).thenReturn(Optional.of(searchPagination));
        final SearchPagination result = scrapeMovieSearchPort.extractPaginationInfo();
        assertThat(result, is(equalTo(searchPagination)));
    }

}

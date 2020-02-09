package io.inouty.jswdb.usecases;

import io.inouty.jswdb.domain.SearchPagination;
import io.inouty.jswdb.usecases.exceptions.PaginationInfoNotFoundException;
import io.inouty.jswdb.usecases.ports.scrapers.MoviesSearchScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScrapeMoviesPaginationUseCaseTest {

    private SearchPagination searchPagination;
    private MoviesSearchScraper moviesSearchScraper;
    private ScrapeMoviesPaginationUseCase scrapeMoviesPaginationUseCase;

    @BeforeEach
    void setUp() {
        searchPagination = searchPagination
                .builder()
                .withStartRecord(0)
                .withEndRecord(200)
                .withRecordsCount(10751)
                .withNextPageUrl(Optional.of("http://search/title/?release_date=1970-12-31,2018-01-01&start=51&ref_=adv_nxt"))
                .build();
        moviesSearchScraper = mock(MoviesSearchScraper.class);
        scrapeMoviesPaginationUseCase = new ScrapeMoviesPaginationUseCase(moviesSearchScraper);
    }

    @AfterEach
    void tearDown() {
        searchPagination = null;
        moviesSearchScraper = null;
        scrapeMoviesPaginationUseCase = null;
    }

    @Test
    void execute() {
        when(moviesSearchScraper.extractPagination()).thenReturn(Optional.of(searchPagination));
        final SearchPagination result = scrapeMoviesPaginationUseCase.execute();
        assertThat(result, is(equalTo(searchPagination)));
    }

    @Test
    void executeShouldThrowPaginationInfoNotFoundException() {
        when(moviesSearchScraper.extractPagination()).thenReturn(Optional.empty());
        assertThrows(PaginationInfoNotFoundException.class, () -> scrapeMoviesPaginationUseCase.execute());
    }
}

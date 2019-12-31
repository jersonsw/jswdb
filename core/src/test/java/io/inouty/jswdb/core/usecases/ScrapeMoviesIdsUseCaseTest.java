package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.usecases.exceptions.NoMoviesIdsFoundException;
import io.inouty.jswdb.core.usecases.ports.scrapers.MoviesSearchScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScrapeMoviesIdsUseCaseTest {

    private MoviesSearchScraper moviesSearchScraper;
    private ScrapeMoviesIdsUseCase scrapeMoviesIdsUseCase;
    private Set<String> moviesIds;


    @BeforeEach
    void setUp() {
        moviesIds = new HashSet<>() {{
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
        moviesSearchScraper = mock(MoviesSearchScraper.class);
        scrapeMoviesIdsUseCase = new ScrapeMoviesIdsUseCase(moviesSearchScraper);
    }

    @AfterEach
    void tearDown() {
        moviesIds = null;
        moviesSearchScraper = null;
        scrapeMoviesIdsUseCase = null;
    }

    @Test
    void execute() {
        when(moviesSearchScraper.extractIds()).thenReturn(moviesIds);
        Set<String> result = scrapeMoviesIdsUseCase.execute();
        assertThat(result, containsInAnyOrder(moviesIds.toArray()));
    }

    @Test()
    public void executeShouldThrowNoMoviesIdsFoundException() {
        when(moviesSearchScraper.extractIds()).thenReturn(Collections.emptySet());
        Assertions.assertThrows(NoMoviesIdsFoundException.class, () -> scrapeMoviesIdsUseCase.execute());
    }
}
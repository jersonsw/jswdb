package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.movie.GenreDto;
import io.inouty.jswdb.core.usecases.exceptions.NoGenresFoundException;
import io.inouty.jswdb.core.usecases.ports.scrapers.MovieItemScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScrapeMovieGenresUseCaseTest {

    private MovieItemScraper movieItemScraper;
    private ScrapeMovieGenresUseCase scrapeMovieGenresUseCase;
    private Set<GenreDto> genres;

    private GenreDto createGenre(Long id, String name) {
        return GenreDto.builder().withId(id).withName(name).build();
    }

    @BeforeEach
    void setUp() {
        movieItemScraper = mock(MovieItemScraper.class);
        scrapeMovieGenresUseCase = new ScrapeMovieGenresUseCase(movieItemScraper);
        genres = new HashSet<>() {{
            add(createGenre(1L, "Adventure"));
            add(createGenre(2L, "Sci-Fi"));
            add(createGenre(3L, "Action"));
            add(createGenre(4L, "Thriller"));
            add(createGenre(5L, "Drama"));
            add(createGenre(6L, "Comedy"));
        }};
    }

    @AfterEach
    void tearDown() {
        genres = null;
        movieItemScraper = null;
        scrapeMovieGenresUseCase = null;
    }

    @Test
    void execute() {
        when(movieItemScraper.extractGenres(anySet())).thenReturn(genres);
        Set<GenreDto> result = scrapeMovieGenresUseCase.execute(anySet());
        assertThat(result, containsInAnyOrder(genres.toArray()));
    }

    @Test()
    public void executeShouldThrowNoMovieGenresFoundException() {
        when(movieItemScraper.extractGenres(anySet())).thenReturn(Collections.emptySet());
        Assertions.assertThrows(NoGenresFoundException.class, () -> scrapeMovieGenresUseCase.execute(anySet()));
    }
}
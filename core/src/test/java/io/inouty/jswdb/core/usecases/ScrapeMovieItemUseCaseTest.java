package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.ports.input.ScrapeMovieItemPort;
import io.inouty.jswdb.core.ports.output.MovieItemScraperPort;
import io.inouty.jswdb.core.ports.usecases.ScrapeMovieItemUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScrapeMovieItemUseCaseTest {

    private MovieItemScraperPort movieItemScraperPort;
    private ScrapeMovieItemPort scrapeMovieItemPort;
    private Set<Genre> genres;
    private Movie movie;

    private Genre createGenre(Long id, String name) {
        return Genre.builder().withId(id).withName(name).build();
    }

    @BeforeEach
    void setUp() {
        movieItemScraperPort = mock(MovieItemScraperPort.class);
        scrapeMovieItemPort = new ScrapeMovieItemUseCase(movieItemScraperPort);
        movie = Movie
                .builder()
                .withId("tt4154796")
                .withTitle("Avengers: Endgame")
                .withProfileImg("https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@")
                .withCertificate("F")
                .withDuration(Time.valueOf("03:20:00"))
                .withMetaScore(78)
                .withYear(2019)
                .withRatingCount(630776L)
                .withRatingAvg(8.5)
                .withSummary("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.")
                .withTrailerUrl("/video/imdb/vi2163260441?playlistId=tt4154796&ref_=tt_ov_vi")
                .build();
        genres = new HashSet<Genre>() {{
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
        movieItemScraperPort = null;
        scrapeMovieItemPort = null;
        genres = null;
    }

    @Test
    void extractGenresTest() {
        when(movieItemScraperPort.extractGenres(anySet())).thenReturn(genres);
        Set<Genre> result = scrapeMovieItemPort.extractGenres(anySet());
        assertThat(result, containsInAnyOrder(genres.toArray()));
    }


    @Test
    void execute() {
        when(movieItemScraperPort.extractBaseInfo()).thenReturn(Optional.of(movie));
        Movie movieResult = scrapeMovieItemPort.extractBaseInfo();
        assertThat(movieResult, is(equalTo(movie)));
    }

}

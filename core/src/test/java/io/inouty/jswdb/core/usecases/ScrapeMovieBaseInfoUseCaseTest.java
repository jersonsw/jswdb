package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.movie.MovieDto;
import io.inouty.jswdb.core.usecases.ports.scrapers.MovieItemScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScrapeMovieBaseInfoUseCaseTest {

    private MovieDto movie;
    private MovieItemScraper movieItemScraper;

    @BeforeEach
    void setUp() {
        movieItemScraper = mock(MovieItemScraper.class);
        movie = MovieDto
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
    }

    @AfterEach
    void tearDown() {
        movie = null;
        movieItemScraper = null;
    }

    @Test
    void execute() {
        when(movieItemScraper.extractBaseInfo()).thenReturn(movie);
        final ScrapeMovieBaseInfoUseCase scrapeMovieBaseInfoUseCase = new ScrapeMovieBaseInfoUseCase(movieItemScraper);
        MovieDto movieResult = scrapeMovieBaseInfoUseCase.execute();
        assertAll(() -> {
            assertThat(movieResult.getId(), is(equalTo("tt4154796")));
            assertThat(movieResult.getTitle(), is(equalTo("Avengers: Endgame")));
        });
    }
}
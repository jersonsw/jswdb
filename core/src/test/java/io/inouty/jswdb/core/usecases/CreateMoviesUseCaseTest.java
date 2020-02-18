package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.ports.input.CreateMoviesPort;
import io.inouty.jswdb.core.ports.output.MoviesPersistencePort;
import io.inouty.jswdb.core.ports.usecases.CreateMoviesUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateMoviesUseCaseTest {

    private Set<Movie> movies;
    private Movie endgame;
    private MoviesPersistencePort moviesPersistencePort;
    private CreateMoviesPort createMoviesPort;

    @BeforeEach()
    public void setUp() {
        endgame = Movie
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

        final Movie jocker = Movie
                .builder()
                .withId("tt7286456")
                .withTitle("Jocker")
                .withProfileImg("https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@")
                .withCertificate("F")
                .withDuration(Time.valueOf("03:20:00"))
                .withMetaScore(59)
                .withYear(2019)
                .withRatingCount(561466L)
                .withRatingAvg(8.7)
                .withSummary("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.")
                .withTrailerUrl("/video/imdb/vi1723318041?playlistId=tt7286456&ref_=tt_ov_vi")
                .build();

        final Movie captainMarvel = Movie
                .builder()
                .withId("tt4154664")
                .withTitle("Captain Marvel")
                .withProfileImg("https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@")
                .withCertificate("F")
                .withDuration(Time.valueOf("03:20:00"))
                .withMetaScore(64)
                .withYear(2019)
                .withRatingCount(384767L)
                .withRatingAvg(6.9)
                .withSummary("Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.")
                .withTrailerUrl("/video/imdb/vi1723318041?playlistId=tt7286456&ref_=tt_ov_vi")
                .build();
        movies = new HashSet<>() {{
            add(endgame);
            add(jocker);
            add(captainMarvel);
        }};
        moviesPersistencePort = mock(MoviesPersistencePort.class);
        createMoviesPort = new CreateMoviesUseCase(moviesPersistencePort);
    }

    @AfterEach()
    public void tearDown() {
        endgame = null;
        movies = null;
        moviesPersistencePort = null;
        createMoviesPort = null;
    }

    @Test()
    public void saveAllTest() {
        when(moviesPersistencePort.saveAll(anySet())).thenReturn(movies);
        final Set<Movie> saved = createMoviesPort.saveAll(movies);
        assertThat(saved, containsInAnyOrder(movies.toArray()));
    }

    @Test()
    public void saveTest() {
        when(moviesPersistencePort.save(any(Movie.class))).thenReturn(endgame);
        final Movie saved = createMoviesPort.save(endgame);
        assertThat(saved, is(equalTo(endgame)));
    }

}

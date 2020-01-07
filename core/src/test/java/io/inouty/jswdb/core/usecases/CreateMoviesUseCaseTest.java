package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.entities.movie.MovieDto;
import io.inouty.jswdb.core.usecases.ports.repositories.MoviesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateMoviesUseCaseTest {

    private List<MovieDto> movies;

    @BeforeEach()
    public void setUp() {
        final MovieDto endgame = MovieDto
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

        final MovieDto jocker = MovieDto
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

        final MovieDto captainMarvel = MovieDto
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
        movies = new ArrayList<>() {{
            add(endgame);
            add(jocker);
            add(captainMarvel);
        }};
    }

    @AfterEach()
    public void tearDown() {
        movies = null;
    }

    @Test()
    public void execute() {
        final MoviesRepository moviesRepository = mock(MoviesRepository.class);
        when(moviesRepository.saveAll(Mockito.anyList())).thenReturn(movies);
        final CreateMoviesUseCase createMoviesUseCase = new CreateMoviesUseCase(moviesRepository);
        final List<MovieDto> saved = createMoviesUseCase.execute(movies);
        assertThat(saved, containsInAnyOrder(movies.toArray()));
    }


}

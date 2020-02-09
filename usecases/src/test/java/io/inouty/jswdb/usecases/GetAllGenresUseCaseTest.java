package io.inouty.jswdb.usecases;

import io.inouty.jswdb.domain.movie.GenreDto;
import io.inouty.jswdb.usecases.exceptions.NoGenresFoundException;
import io.inouty.jswdb.usecases.ports.repositories.GenresRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllGenresUseCaseTest {

    private List<GenreDto> genres;
    private GenresRepository genresRepository;
    private GetAllGenresUseCase getAllGenresUseCase;

    private GenreDto createGenre(Long id, String name) {
        return GenreDto.builder().withId(id).withName(name).build();
    }

    @BeforeEach
    public void setUp() {
        genresRepository = mock(GenresRepository.class);
        getAllGenresUseCase = new GetAllGenresUseCase(genresRepository);
        genres = new ArrayList<>() {{
            add(createGenre(1L, "Adventure"));
            add(createGenre(2L, "Sci-Fi"));
            add(createGenre(3L, "Action"));
            add(createGenre(4L, "Thriller"));
            add(createGenre(5L, "Drama"));
            add(createGenre(6L, "Comedy"));
        }};
    }

    @AfterEach()
    public void tearDown() {
        genres = null;
        genresRepository = null;
        getAllGenresUseCase = null;
    }

    @Test
    public void execute() {
        when(genresRepository.findAll()).thenReturn(genres);
        assertThat(getAllGenresUseCase.execute(), containsInAnyOrder(genres.toArray()));
    }

    @Test()
    public void executeShouldThrowNoGenresFoundException() {
        when(genresRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NoGenresFoundException.class, () -> getAllGenresUseCase.execute());
    }
}

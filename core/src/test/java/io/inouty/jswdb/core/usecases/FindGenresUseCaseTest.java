package io.inouty.jswdb.core.usecases;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.core.exceptions.GenreNotFoundException;
import io.inouty.jswdb.core.ports.output.GenresPersistencePort;
import io.inouty.jswdb.core.ports.usecases.FindGenresUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindGenresUseCaseTest {

    private Set<Genre> genres;
    private Genre adventure;
    private GenresPersistencePort genresPersistencePort;
    private FindGenresUseCase findGenresUseCase;

    private Genre createGenre(Long id, String name) {
        return Genre.builder().withId(id).withName(name).build();
    }

    @BeforeEach
    public void setUp() {
        genresPersistencePort = mock(GenresPersistencePort.class);
        findGenresUseCase = new FindGenresUseCase(genresPersistencePort);
        adventure = createGenre(1L, "Adventure");
        genres = new HashSet<>() {{
            add(adventure);
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
        adventure = null;
        genresPersistencePort = null;
        findGenresUseCase = null;
    }

    @Test
    public void findAllTest() {
        when(genresPersistencePort.findAll()).thenReturn(genres);
        assertThat(findGenresUseCase.findAll(), containsInAnyOrder(genres.toArray()));
    }

    public void findOneTest() {
        when(genresPersistencePort.findOne(anyLong())).thenReturn(Optional.of(adventure));
        assertThat(findGenresUseCase.findOne(adventure.getId()), is(equalTo(adventure)));
    }

    @Test()
    public void executeShouldThrowNoGenresFoundException() {
        when(genresPersistencePort.findAll()).thenReturn(Collections.emptySet());
        Assertions.assertThrows(GenreNotFoundException.class, () -> findGenresUseCase.findAll());
    }
}

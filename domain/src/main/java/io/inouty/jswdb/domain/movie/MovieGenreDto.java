package io.inouty.jswdb.domain.movie;


import java.io.Serializable;
import java.util.Objects;

public final class MovieGenreDto implements Serializable {

    private final MovieGenreIdDto id;
    private final MovieDto movie;
    private final GenreDto genre;

    private MovieGenreDto(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.genre = builder.genre;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieGenreDto mg) {
        return builder()
                .withId(mg.id)
                .withMovie(mg.movie)
                .withGenre(mg.genre);
    }

    public MovieGenreIdDto getId() {
        return id;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public GenreDto getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieGenreDto that = (MovieGenreDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, genre);
    }

    public static final class Builder {

        private MovieGenreIdDto id;
        private MovieDto movie;
        private GenreDto genre;

        private Builder() {}

        public Builder withId(MovieGenreIdDto id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieDto movie) {
            this.movie = movie;
            return this;
        }

        public Builder withGenre(GenreDto genre) {
            this.genre = genre;
            return this;
        }

        public MovieGenreDto build() {
            return new MovieGenreDto(this);
        }
    }
}

package io.inouty.jswdb.core.domain.movie;


import java.io.Serializable;
import java.util.Objects;

public final class MovieGenre implements Serializable {

    private final MovieGenreId id;
    private final Movie movie;
    private final Genre genre;

    private MovieGenre(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.genre = builder.genre;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieGenre mg) {
        return builder()
                .withId(mg.id)
                .withMovie(mg.movie)
                .withGenre(mg.genre);
    }

    public MovieGenreId getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieGenre that = (MovieGenre) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, genre);
    }

    public static final class Builder {

        private MovieGenreId id;
        private Movie movie;
        private Genre genre;

        private Builder() {}

        public Builder withId(MovieGenreId id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public MovieGenre build() {
            return new MovieGenre(this);
        }
    }
}

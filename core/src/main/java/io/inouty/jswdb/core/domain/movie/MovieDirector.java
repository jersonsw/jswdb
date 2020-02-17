package io.inouty.jswdb.core.domain.movie;


import java.io.Serializable;
import java.util.Objects;

public final class MovieDirector implements Serializable {

    private final MovieDirectorId id;
    private final Movie movie;
    private final Director director;

    private MovieDirector(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.director = builder.director;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieDirector md) {
        return builder()
                .withId(md.id)
                .withMovie(md.movie)
                .withDirector(md.director);
    }

    public MovieDirectorId getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Director getDirector() {
        return director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDirector that = (MovieDirector) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, director);
    }

    public static final class Builder {

        private MovieDirectorId id;
        private Movie movie;
        private Director director;

        private Builder() {}

        public Builder withId(MovieDirectorId id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withDirector(Director director) {
            this.director = director;
            return this;
        }

        public MovieDirector build() {
            return new MovieDirector(this);
        }
    }
}

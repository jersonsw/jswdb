package io.inouty.jswdb.core.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieDirectorId implements Serializable {

    private final String movieId;
    private final String directorId;

    private MovieDirectorId(Builder builder) {
        this.movieId = builder.movieId;
        this.directorId = builder.directorId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieDirectorId mdId) {
        return builder()
                .withMovieId(mdId.movieId)
                .withDirectorId(mdId.directorId);
    }

    public String getMovieId() {
        return movieId;
    }

    public String getDirectorId() {
        return directorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDirectorId that = (MovieDirectorId) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(directorId, that.directorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, directorId);
    }


    public static final class Builder {

        private String movieId;
        private String directorId;

        private Builder() {}

        public Builder withMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder withDirectorId(String directorId) {
            this.directorId = directorId;
            return this;
        }

        public MovieDirectorId build() {
            return new MovieDirectorId(this);
        }

    }
}

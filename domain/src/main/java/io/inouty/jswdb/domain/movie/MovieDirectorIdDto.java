package io.inouty.jswdb.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieDirectorIdDto implements Serializable {

    private final String movieId;
    private final String directorId;

    private MovieDirectorIdDto(Builder builder) {
        this.movieId = builder.movieId;
        this.directorId = builder.directorId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieDirectorIdDto mdId) {
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
        MovieDirectorIdDto that = (MovieDirectorIdDto) o;
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

        public MovieDirectorIdDto build() {
            return new MovieDirectorIdDto(this);
        }

    }
}

package io.inouty.jswdb.data.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieDirectorId implements Serializable {

    @Column(name = "movie_id", nullable = false, length = 20)
    private String movieId;

    @Column(name = "director_id", nullable = false, length = 20)
    private String directorId;

    public MovieDirectorId() {
    }

    public MovieDirectorId(String movieId, String directorId) {
        this.movieId = movieId;
        this.directorId = directorId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
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


    public static final class Builder {

        private String movieId;
        private String directorId;

        private Builder() {
        }

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

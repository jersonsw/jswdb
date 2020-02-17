package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.MovieDirectorId;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieDirectorIdEntity implements Serializable, Mapeable<MovieDirectorId> {

    @Column(name = "movie_id", nullable = false, length = 20)
    private String movieId;

    @Column(name = "director_id", nullable = false, length = 20)
    private String directorId;

    public MovieDirectorIdEntity() {
    }

    public MovieDirectorIdEntity(String movieId, String directorId) {
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
        MovieDirectorIdEntity that = (MovieDirectorIdEntity) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(directorId, that.directorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, directorId);
    }


    private MovieDirectorIdEntity(Builder builder) {
        this.movieId = builder.movieId;
        this.directorId = builder.directorId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieDirectorIdEntity mdId) {
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

        public MovieDirectorIdEntity build() {
            return new MovieDirectorIdEntity(this);
        }

    }

    @Override
    public MovieDirectorId to() {
        return EntityMapper.to(this);
    }
}

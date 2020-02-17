package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.MovieGenreId;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieGenreIdEntity implements Serializable, Mapeable<MovieGenreId> {

    @Column(name = "movie_id", nullable = false, length = 20)
    private String movieId;

    @Column(name = "genre_id", nullable = false, length = 20, insertable = false, updatable = false)
    private Long genreId;

    public MovieGenreIdEntity() {}

    private MovieGenreIdEntity(Builder builder) {
        this.movieId = builder.movieId;
        this.genreId = builder.genreId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieGenreIdEntity that = (MovieGenreIdEntity) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, genreId);
    }

    public static final class Builder {

        private String movieId;
        private Long genreId;

        private Builder() {}

        public Builder withMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder withGenreId(Long genreId) {
            this.genreId = genreId;
            return this;
        }

        public MovieGenreIdEntity build() {
            return new MovieGenreIdEntity(this);
        }
    }

    @Override
    public MovieGenreId to() {
        return EntityMapper.to(this);
    }
}

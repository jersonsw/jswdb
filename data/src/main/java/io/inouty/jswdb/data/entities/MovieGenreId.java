package io.inouty.jswdb.data.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieGenreId implements Serializable {

    @Column(name = "movie_id", nullable = false, length = 20)
    private String movieId;

    @Column(name = "genre_id", nullable = false, length = 20, insertable = false, updatable = false)
    private Long genreId;

    public MovieGenreId() {
    }

    public MovieGenreId(String movieId, Long genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    private MovieGenreId(Builder builder) {
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
        MovieGenreId that = (MovieGenreId) o;
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

        private Builder() {
        }

        public Builder withMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder withGenreId(Long genreId) {
            this.genreId = genreId;
            return this;
        }

        public MovieGenreId build() {
            return new MovieGenreId(this);
        }

    }
}

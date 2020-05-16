package io.inouty.jswdb.core.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieGenreId implements Serializable {

    private final String movieId;
    private final Long genreId;

    private MovieGenreId(Builder builder) {
        this.movieId = builder.movieId;
        this.genreId = builder.genreId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(MovieGenreId mgId){
        return builder()
                .withMovieId(mgId.movieId)
                .withGenreId(mgId.genreId);
    }

    public String getMovieId() {
        return movieId;
    }

    public Long getGenreId() {
        return genreId;
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

        private Builder() {}

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

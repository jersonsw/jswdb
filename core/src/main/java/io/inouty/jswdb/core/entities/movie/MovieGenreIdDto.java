package io.inouty.jswdb.core.entities.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieGenreIdDto implements Serializable {

    private final String movieId;
    private final Long genreId;

    private MovieGenreIdDto(Builder builder) {
        this.movieId = builder.movieId;
        this.genreId = builder.genreId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(MovieGenreIdDto mgId){
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
        MovieGenreIdDto that = (MovieGenreIdDto) o;
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

        public MovieGenreIdDto build() {
            return new MovieGenreIdDto(this);
        }

    }

}

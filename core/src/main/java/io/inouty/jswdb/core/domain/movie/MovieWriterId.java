package io.inouty.jswdb.core.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieWriterId implements Serializable {

    private final String movieId;
    private final String writerId;

    private MovieWriterId(Builder builder) {
        this.movieId = builder.movieId;
        this.writerId = builder.writerId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(MovieWriterId mwId){
        return builder()
                .withMovieId(mwId.movieId)
                .withMovieId(mwId.writerId);
    }

    public String getMovieId() {
        return movieId;
    }

    public String getWriterId() {
        return writerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieWriterId that = (MovieWriterId) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(writerId, that.writerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, writerId);
    }


    public static final class Builder {

        private String movieId;
        private String writerId;

        private Builder() {}

        public Builder withMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder withWriterId(String writerId) {
            this.writerId = writerId;
            return this;
        }

        public MovieWriterId build() {
            return new MovieWriterId(this);
        }

    }

}

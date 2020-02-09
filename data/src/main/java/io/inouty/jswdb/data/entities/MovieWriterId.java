package io.inouty.jswdb.data.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieWriterId implements Serializable {

    @Column(name = "movie_id", nullable = false, length = 20)
    private String movieId;

    @Column(name = "writer_id", nullable = false, length = 20)
    private String writerId;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public MovieWriterId() {
    }

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

    public MovieWriterId(String movieId, String writerId) {
        this.movieId = movieId;
        this.writerId = writerId;
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

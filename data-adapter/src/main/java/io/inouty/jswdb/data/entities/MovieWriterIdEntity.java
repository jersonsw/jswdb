package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.MovieWriterId;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mappable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieWriterIdEntity implements Serializable, Mappable<MovieWriterId> {

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

    public MovieWriterIdEntity() {
    }

    private MovieWriterIdEntity(Builder builder) {
        this.movieId = builder.movieId;
        this.writerId = builder.writerId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(MovieWriterIdEntity mwId){
        return builder()
                .withMovieId(mwId.movieId)
                .withMovieId(mwId.writerId);
    }

    public MovieWriterIdEntity(String movieId, String writerId) {
        this.movieId = movieId;
        this.writerId = writerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieWriterIdEntity that = (MovieWriterIdEntity) o;
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

        public MovieWriterIdEntity build() {
            return new MovieWriterIdEntity(this);
        }

    }

    @Override
    public MovieWriterId to() {
        return EntityMapper.to(this);
    }

}

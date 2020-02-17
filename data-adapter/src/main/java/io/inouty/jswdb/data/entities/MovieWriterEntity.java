package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.MovieWriter;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.*;

@Entity(name = "movieWriter")
@Table(schema = "works", name = "movies_writers")
public class MovieWriterEntity implements Mapeable<MovieWriter> {

    @EmbeddedId
    private MovieWriterIdEntity id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private MovieEntity movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("writerId")
    private WriterEntity writer;

    @Column(length = 120)
    private String credits;

    public MovieWriterEntity() {
    }

    private MovieWriterEntity(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.writer = builder.writer;
        this.credits = builder.credits;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieWriterEntity mw) {
        return builder()
                .withId(mw.id)
                .withMovie(mw.movie)
                .withWriter(mw.writer)
                .withCredits(mw.credits);
    }

    public MovieWriterEntity(MovieWriterIdEntity movieWriterId, MovieEntity movie, WriterEntity writer, String credits) {
        this.id = movieWriterId;
        this.movie = movie;
        this.writer = writer;
        this.credits = credits;
    }

    public MovieWriterIdEntity getId() {
        return id;
    }

    public void setId(MovieWriterIdEntity id) {
        this.id = id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public WriterEntity getWriter() {
        return writer;
    }

    public void setWriter(WriterEntity writer) {
        this.writer = writer;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }


    public static final class Builder {

        private MovieWriterIdEntity id;
        private MovieEntity movie;
        private WriterEntity writer;
        private String credits;

        private Builder() {
        }

        public Builder withId(MovieWriterIdEntity id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieEntity movieEntity) {
            this.movie = movieEntity;
            return this;
        }

        public Builder withWriter(WriterEntity writerEntity) {
            this.writer = writerEntity;
            return this;
        }

        public Builder withCredits(String credits) {
            this.credits = credits;
            return this;
        }

        public MovieWriterEntity build() {
            return new MovieWriterEntity(this);
        }
    }

    @Override
    public MovieWriter to() {
        return EntityMapper.to(this);
    }

}

package io.inouty.jswdb.data.entities;

import javax.persistence.*;

@Entity
@Table(schema = "works", name = "movies_writers")
public class MovieWriter {

    @EmbeddedId
    private MovieWriterId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("writerId")
    private Writer writer;

    @Column(length = 120)
    private String credits;

    public MovieWriter() {
    }

    private MovieWriter(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.writer = builder.writer;
        this.credits = builder.credits;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieWriter mw) {
        return builder()
                .withId(mw.id)
                .withMovie(mw.movie)
                .withWriter(mw.writer)
                .withCredits(mw.credits);
    }

    public MovieWriter(MovieWriterId movieWriterId, Movie movie, Writer writer, String credits) {
        this.id = movieWriterId;
        this.movie = movie;
        this.writer = writer;
        this.credits = credits;
    }

    public MovieWriterId getId() {
        return id;
    }

    public void setId(MovieWriterId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }


    public static final class Builder {

        private MovieWriterId id;
        private Movie movie;
        private Writer writer;
        private String credits;

        private Builder() {
        }

        public Builder withId(MovieWriterId id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withWriter(Writer writer) {
            this.writer = writer;
            return this;
        }

        public Builder withCredits(String credits) {
            this.credits = credits;
            return this;
        }

        public MovieWriter build() {
            return new MovieWriter(this);
        }
    }

}

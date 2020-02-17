package io.inouty.jswdb.core.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieWriter implements Serializable {

    private final MovieWriterId id;
    private final Movie movie;
    private final Writer writer;
    private final String credits;

    private MovieWriter(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.writer = builder.writer;
        this.credits = builder.credits;
    }

    public MovieWriterId getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Writer getWriter() {
        return writer;
    }

    public String getCredits() {
        return credits;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieWriter that = (MovieWriter) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(writer, that.writer) &&
                Objects.equals(credits, that.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, writer, credits);
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

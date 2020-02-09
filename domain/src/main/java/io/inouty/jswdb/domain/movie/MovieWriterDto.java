package io.inouty.jswdb.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieWriterDto implements Serializable {

    private final MovieWriterIdDto id;
    private final MovieDto movie;
    private final WriterDto writer;
    private final String credits;

    private MovieWriterDto(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.writer = builder.writer;
        this.credits = builder.credits;
    }

    public MovieWriterIdDto getId() {
        return id;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public WriterDto getWriter() {
        return writer;
    }

    public String getCredits() {
        return credits;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieWriterDto mw) {
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
        MovieWriterDto that = (MovieWriterDto) o;
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

        private MovieWriterIdDto id;
        private MovieDto movie;
        private WriterDto writer;
        private String credits;

        private Builder() {
        }

        public Builder withId(MovieWriterIdDto id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieDto movie) {
            this.movie = movie;
            return this;
        }

        public Builder withWriter(WriterDto writer) {
            this.writer = writer;
            return this;
        }

        public Builder withCredits(String credits) {
            this.credits = credits;
            return this;
        }

        public MovieWriterDto build() {
            return new MovieWriterDto(this);
        }
    }
}

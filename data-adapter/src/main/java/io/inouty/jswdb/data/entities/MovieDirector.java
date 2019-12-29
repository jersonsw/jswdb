package io.inouty.jswdb.data.entities;

import javax.persistence.*;

@Entity
@Table(schema = "works", name = "movies_directors")
public class MovieDirector {

    @EmbeddedId
    private MovieDirectorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("directorId")
    private Director director;

    public MovieDirector() {
    }

    private MovieDirector(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.director = builder.director;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieDirector md) {
        return builder()
                .withId(md.id)
                .withMovie(md.movie)
                .withDirector(md.director);
    }

    public MovieDirector(MovieDirectorId id, Movie movie, Director director) {
        this.id = id;
        this.movie = movie;
        this.director = director;
    }

    public MovieDirectorId getId() {
        return id;
    }

    public void setId(MovieDirectorId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public static final class Builder {

        private MovieDirectorId id;
        private Movie movie;
        private Director director;

        private Builder() {}

        public Builder withId(MovieDirectorId id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withDirector(Director director) {
            this.director = director;
            return this;
        }

        public MovieDirector build() {
            return new MovieDirector(this);
        }
    }
}

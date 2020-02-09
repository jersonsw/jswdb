package io.inouty.jswdb.data.entities;

import javax.persistence.*;

@Entity
@Table(schema = "works", name = "movies_genres")
public class MovieGenre {

    @EmbeddedId
    private MovieGenreId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("genreId")
    private Genre genre;

    public MovieGenre() {
    }

    private MovieGenre(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.genre = builder.genre;
    }

    public static Builder builder() {
        return new Builder();
    }

    public MovieGenre(MovieGenreId movieGenreId, Movie movie, Genre genre) {
        this.id = movieGenreId;
        this.movie = movie;
        this.genre = genre;
    }

    public MovieGenreId getId() {
        return id;
    }

    public void setId(MovieGenreId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public static final class Builder {

        private MovieGenreId id;
        private Movie movie;
        private Genre genre;

        private Builder() {
        }

        public Builder withId(MovieGenreId id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public MovieGenre build() {
            return new MovieGenre(this);
        }
    }

}

package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.MovieGenre;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.*;

@Entity(name="movieGenre")
@Table(schema = "works", name = "movies_genres")
public class MovieGenreEntity implements Mapeable<MovieGenre> {

    @EmbeddedId
    private MovieGenreIdEntity id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private MovieEntity movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("genreId")
    private GenreEntity genre;

    public MovieGenreEntity() {
    }

    private MovieGenreEntity(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.genre = builder.genre;
    }

    public static Builder builder() {
        return new Builder();
    }

    public MovieGenreIdEntity getId() {
        return id;
    }

    public void setId(MovieGenreIdEntity id) {
        this.id = id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public static final class Builder {

        private MovieGenreIdEntity id;
        private MovieEntity movie;
        private GenreEntity genre;

        private Builder() {
        }

        public Builder withId(MovieGenreIdEntity id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieEntity movie) {
            this.movie = movie;
            return this;
        }

        public Builder withGenre(GenreEntity genre) {
            this.genre = genre;
            return this;
        }

        public MovieGenreEntity build() {
            return new MovieGenreEntity(this);
        }
    }

    @Override
    public MovieGenre to() {
        return EntityMapper.to(this);
    }
}

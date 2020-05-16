package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.MovieDirector;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mappable;

import javax.persistence.*;

@Entity(name = "movieDirector")
@Table(schema = "works", name = "movies_directors")
public class MovieDirectorEntity implements Mappable<MovieDirector> {

    @EmbeddedId
    private MovieDirectorIdEntity id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private MovieEntity movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("directorId")
    private DirectorEntity director;

    public MovieDirectorEntity() {
    }

    private MovieDirectorEntity(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.director = builder.director;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieDirectorEntity md) {
        return builder()
                .withId(md.id)
                .withMovie(md.movie)
                .withDirector(md.director);
    }

    public MovieDirectorEntity(MovieDirectorIdEntity id, MovieEntity movie, DirectorEntity director) {
        this.id = id;
        this.movie = movie;
        this.director = director;
    }

    public MovieDirectorIdEntity getId() {
        return id;
    }

    public void setId(MovieDirectorIdEntity id) {
        this.id = id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public DirectorEntity getDirector() {
        return director;
    }

    public void setDirector(DirectorEntity director) {
        this.director = director;
    }

    public static final class Builder {

        private MovieDirectorIdEntity id;
        private MovieEntity movie;
        private DirectorEntity director;

        private Builder() {
        }

        public Builder withId(MovieDirectorIdEntity id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieEntity movie) {
            this.movie = movie;
            return this;
        }

        public Builder withDirector(DirectorEntity director) {
            this.director = director;
            return this;
        }

        public MovieDirectorEntity build() {
            return new MovieDirectorEntity(this);
        }
    }

    @Override
    public MovieDirector to() {
        return EntityMapper.to(this);
    }
}

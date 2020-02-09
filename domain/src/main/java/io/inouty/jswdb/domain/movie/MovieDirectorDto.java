package io.inouty.jswdb.domain.movie;


import java.io.Serializable;
import java.util.Objects;

public final class MovieDirectorDto implements Serializable {

    private final MovieDirectorIdDto id;
    private final MovieDto movie;
    private final DirectorDto director;

    private MovieDirectorDto(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.director = builder.director;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieDirectorDto md) {
        return builder()
                .withId(md.id)
                .withMovie(md.movie)
                .withDirector(md.director);
    }

    public MovieDirectorIdDto getId() {
        return id;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public DirectorDto getDirector() {
        return director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDirectorDto that = (MovieDirectorDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, director);
    }

    public static final class Builder {

        private MovieDirectorIdDto id;
        private MovieDto movie;
        private DirectorDto director;

        private Builder() {}

        public Builder withId(MovieDirectorIdDto id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieDto movie) {
            this.movie = movie;
            return this;
        }

        public Builder withDirector(DirectorDto director) {
            this.director = director;
            return this;
        }

        public MovieDirectorDto build() {
            return new MovieDirectorDto(this);
        }
    }
}

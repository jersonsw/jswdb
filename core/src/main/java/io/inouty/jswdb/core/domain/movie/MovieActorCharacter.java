package io.inouty.jswdb.core.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieActorCharacter implements Serializable {

    private final MovieActorCharacterId id;
    private final Movie movie;
    private final Actor actor;
    private final Character character;

    private MovieActorCharacter(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.actor = builder.actor;
        this.character = builder.character;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieActorCharacter mac) {
        return builder()
                .withId(mac.id)
                .withMovie(mac.movie)
                .withActor(mac.actor)
                .withCharacter(mac.character);
    }

    public MovieActorCharacterId getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Actor getActor() {
        return actor;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieActorCharacter that = (MovieActorCharacter) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(actor, that.actor) &&
                Objects.equals(character, that.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, actor, character);
    }

    public static final class Builder {

        private MovieActorCharacterId id;
        private Movie movie;
        private Actor actor;
        private Character character;

        private Builder() {
        }

        public Builder withId(MovieActorCharacterId id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withActor(Actor actor) {
            this.actor = actor;
            return this;
        }

        public Builder withCharacter(Character character) {
            this.character = character;
            return this;
        }

        public MovieActorCharacter build() {
            return new MovieActorCharacter(this);
        }
    }
}

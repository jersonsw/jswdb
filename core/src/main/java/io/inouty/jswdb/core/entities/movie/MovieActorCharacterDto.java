package io.inouty.jswdb.core.entities.movie;

import java.io.Serializable;
import java.util.Objects;

public final class MovieActorCharacterDto implements Serializable {

    private final MovieActorCharacterIdDto id;
    private final MovieDto movie;
    private final ActorDto actor;
    private final CharacterDto character;

    private MovieActorCharacterDto(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.actor = builder.actor;
        this.character = builder.character;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieActorCharacterDto mac) {
        return builder()
                .withId(mac.id)
                .withMovie(mac.movie)
                .withActor(mac.actor)
                .withCharacter(mac.character);
    }

    public MovieActorCharacterIdDto getId() {
        return id;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public ActorDto getActor() {
        return actor;
    }

    public CharacterDto getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieActorCharacterDto that = (MovieActorCharacterDto) o;
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

        private MovieActorCharacterIdDto id;
        private MovieDto movie;
        private ActorDto actor;
        private CharacterDto character;

        private Builder() {
        }

        public Builder withId(MovieActorCharacterIdDto id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieDto movie) {
            this.movie = movie;
            return this;
        }

        public Builder withActor(ActorDto actor) {
            this.actor = actor;
            return this;
        }

        public Builder withCharacter(CharacterDto character) {
            this.character = character;
            return this;
        }

        public MovieActorCharacterDto build() {
            return new MovieActorCharacterDto(this);
        }
    }
}

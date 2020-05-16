package io.inouty.jswdb.data.entities;
import io.inouty.jswdb.core.domain.movie.MovieActorCharacter;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mappable;

import javax.persistence.*;

@Entity(name="movieActorCharacter")
@Table(schema = "works", name = "movies_actors_characters")
public class MovieActorCharacterEntity implements Mappable<MovieActorCharacter> {

    @EmbeddedId
    private MovieActorCharacterIdEntity id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movie;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActorEntity actor;

    @MapsId("characterId")
    @ManyToOne(fetch = FetchType.LAZY)
    private CharacterEntity character;

    public MovieActorCharacterEntity() {
    }

    private MovieActorCharacterEntity(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.actor = builder.actor;
        this.character = builder.character;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieActorCharacterEntity mac) {
        return builder()
                .withId(mac.id)
                .withMovie(mac.movie)
                .withActor(mac.actor)
                .withCharacter(mac.character);
    }

    public MovieActorCharacterEntity(MovieActorCharacterIdEntity id, MovieEntity movie, ActorEntity actor, CharacterEntity character) {
        this.id = id;
        this.movie = movie;
        this.actor = actor;
        this.character = character;
    }

    public MovieActorCharacterIdEntity getId() {
        return id;
    }

    public void setId(MovieActorCharacterIdEntity id) {
        this.id = id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public ActorEntity getActor() {
        return actor;
    }

    public void setActor(ActorEntity actor) {
        this.actor = actor;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }

    public static final class Builder {

        private MovieActorCharacterIdEntity id;
        private MovieEntity movie;
        private ActorEntity actor;
        private CharacterEntity character;

        private Builder() {
        }

        public Builder withId(MovieActorCharacterIdEntity id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieEntity movie) {
            this.movie = movie;
            return this;
        }

        public Builder withActor(ActorEntity actor) {
            this.actor = actor;
            return this;
        }

        public Builder withCharacter(CharacterEntity character) {
            this.character = character;
            return this;
        }

        public MovieActorCharacterEntity build() {
            return new MovieActorCharacterEntity(this);
        }
    }

    @Override
    public MovieActorCharacter to() {
        return EntityMapper.to(this);
    }

}

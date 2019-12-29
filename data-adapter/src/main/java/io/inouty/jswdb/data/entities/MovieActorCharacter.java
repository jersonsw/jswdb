package io.inouty.jswdb.data.entities;

import javax.persistence.*;

@Entity
@Table(schema = "works", name = "movies_actors_characters")
public class MovieActorCharacter {

    @EmbeddedId
    private MovieActorCharacterId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor;

    @MapsId("characterId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Character character;

    public MovieActorCharacter() {
    }

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

    public MovieActorCharacter(MovieActorCharacterId id, Movie movie, Actor actor, Character character) {
        this.id = id;
        this.movie = movie;
        this.actor = actor;
        this.character = character;
    }

    public MovieActorCharacterId getId() {
        return id;
    }

    public void setId(MovieActorCharacterId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
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

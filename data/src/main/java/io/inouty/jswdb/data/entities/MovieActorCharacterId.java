package io.inouty.jswdb.data.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieActorCharacterId implements Serializable {

    @Column(name = "movie_id", length = 20)
    private String movieId;

    @Column(name = "actor_id", length = 20)
    private String actorId;

    @Column(name = "character_id")
    private Long characterId;

    public MovieActorCharacterId() {
    }

    private MovieActorCharacterId(Builder builder) {
        this.movieId = builder.movieId;
        this.actorId = builder.actorId;
        this.characterId = builder.characterId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(MovieActorCharacterId macId){
        return builder()
                .withMovieId(macId.movieId)
                .withActorId(macId.actorId)
                .withCharacterId(macId.characterId);

    }

    public MovieActorCharacterId(String movieId, String actorId, Long characterId) {
        this.movieId = movieId;
        this.actorId = actorId;
        this.characterId = characterId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieActorCharacterId that = (MovieActorCharacterId) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(actorId, that.actorId) &&
                Objects.equals(characterId, that.characterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId, characterId);
    }

    public static final class Builder {

        private String movieId;
        private String actorId;
        private Long characterId;

        private Builder(){}

        public Builder withMovieId(String movieId){
            this.movieId = movieId;
            return this;
        }

        public Builder withActorId(String actorId){
            this.actorId = actorId;
            return this;
        }

        public Builder withCharacterId(Long characterId){
            this.characterId = characterId;
            return this;
        }

        public MovieActorCharacterId build(){
            return new MovieActorCharacterId(this);
        }

    }

}

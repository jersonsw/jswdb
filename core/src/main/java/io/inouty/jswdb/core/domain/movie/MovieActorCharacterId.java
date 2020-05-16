package io.inouty.jswdb.core.domain.movie;

import java.io.Serializable;
import java.util.Objects;


public final class MovieActorCharacterId implements Serializable {

    private final String movieId;
    private final String actorId;
    private final Long characterId;

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

    public String getMovieId() {
        return movieId;
    }

    public String getActorId() {
        return actorId;
    }

    public Long getCharacterId() {
        return characterId;
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

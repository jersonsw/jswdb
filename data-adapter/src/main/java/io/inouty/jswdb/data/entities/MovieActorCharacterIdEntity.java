package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.MovieActorCharacterId;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieActorCharacterIdEntity implements Serializable, Mapeable<MovieActorCharacterId> {

    @Column(name = "movie_id", length = 20)
    private String movieId;

    @Column(name = "actor_id", length = 20)
    private String actorId;

    @Column(name = "character_id")
    private Long characterId;

    public MovieActorCharacterIdEntity() {}

    private MovieActorCharacterIdEntity(Builder builder) {
        this.movieId = builder.movieId;
        this.actorId = builder.actorId;
        this.characterId = builder.characterId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MovieActorCharacterIdEntity macId) {
        return builder()
                .withMovieId(macId.movieId)
                .withActorId(macId.actorId)
                .withCharacterId(macId.characterId);

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
        MovieActorCharacterIdEntity that = (MovieActorCharacterIdEntity) o;
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

        private Builder() {
        }

        public Builder withMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder withActorId(String actorId) {
            this.actorId = actorId;
            return this;
        }

        public Builder withCharacterId(Long characterId) {
            this.characterId = characterId;
            return this;
        }

        public MovieActorCharacterIdEntity build() {
            return new MovieActorCharacterIdEntity(this);
        }

    }


    @Override
    public MovieActorCharacterId to() {
        return EntityMapper.to(this);
    }
}

package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.Character;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mappable;

import javax.persistence.*;

@Entity(name = "character")
@Table(schema = "works", name = "characters", uniqueConstraints = @UniqueConstraint(name = "characters_uk", columnNames = {"imdb_id", "name"}))
public class CharacterEntity implements Mappable<Character> {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imdb_id", length = 20)
    private String imdbId;

    @Column(nullable = false, length = 120)
    private String name;

    public CharacterEntity() {}

    private CharacterEntity(Builder builder) {
        this.id = builder.id;
        this.imdbId = builder.imdbId;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CharacterEntity characterEntity) {
        return builder()
                .withId(characterEntity.id)
                .withImdbId(characterEntity.imdbId)
                .withName(characterEntity.name);
    }

    public CharacterEntity(String imdbId, String name) {
        this.imdbId = imdbId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {

        private Long id;
        private String imdbId;
        private String name;

        private Builder() {}

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withImdbId(String imdbId) {
            this.imdbId = imdbId;
            return this;
        }

        public CharacterEntity build(){
            return new CharacterEntity(this);
        }

    }

    @Override
    public Character to() {
        return EntityMapper.to(this);
    }
}

package io.inouty.jswdb.data.entities;

import javax.persistence.*;

@Entity
@Table(schema = "works", name = "characters", uniqueConstraints = @UniqueConstraint(name = "characters_uk", columnNames = {"imdb_id", "name"}))
public class Character {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imdb_id", length = 20)
    private String imdbId;

    @Column(nullable = false, length = 120)
    private String name;

    public Character() {}

    private Character(Builder builder) {
        this.id = builder.id;
        this.imdbId = builder.imdbId;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Character character) {
        return builder()
                .withId(character.id)
                .withImdbId(character.imdbId)
                .withName(character.name);
    }

    public Character(String imdbId, String name) {
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

        public Character build(){
            return new Character(this);
        }

    }

}

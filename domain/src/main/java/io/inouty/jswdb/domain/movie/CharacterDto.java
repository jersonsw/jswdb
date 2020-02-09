package io.inouty.jswdb.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class CharacterDto implements Serializable {

    private final Long id;
    private final String imdbId;
    private final String name;

    private CharacterDto(CharacterDto.Builder builder) {
        this.id = builder.id;
        this.imdbId = builder.imdbId;
        this.name = builder.name;
    }

    public static CharacterDto.Builder builder() {
        return new CharacterDto.Builder();
    }

    public static CharacterDto.Builder builder(CharacterDto character) {
        return builder()
                .withId(character.id)
                .withImdbId(character.imdbId)
                .withName(character.name);
    }

    public Long getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterDto character = (CharacterDto) o;
        return Objects.equals(id, character.id) &&
                Objects.equals(imdbId, character.imdbId) &&
                Objects.equals(name, character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdbId, name);
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

        public CharacterDto build(){
            return new CharacterDto(this);
        }

    }
}

package io.inouty.jswdb.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class GenreDto implements Serializable {

    private final Long id;
    private final String name;

    private GenreDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(GenreDto genre) {
        return builder()
                .withId(genre.id)
                .withName(genre.name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDto genre = (GenreDto) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static final class Builder {

        private Long id;
        private String name;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public GenreDto build() {
            return new GenreDto(this);
        }
    }
}

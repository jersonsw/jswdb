package io.inouty.jswdb.core.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class Genre implements Serializable {

    private final Long id;
    private final String name;

    private Genre(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Genre genre) {
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
        Genre genre = (Genre) o;
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

        public Genre build() {
            return new Genre(this);
        }
    }
}

package io.inouty.jswdb.core.entities.movie;

import java.io.Serializable;
import java.util.Objects;

public final class DirectorDto implements Serializable {

    private final String id;
    private final String fullName;

    private DirectorDto(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(DirectorDto director){
        return builder()
                .withId(director.id)
                .withFullName(director.fullName);
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorDto director = (DirectorDto) o;
        return Objects.equals(id, director.id) &&
                Objects.equals(fullName, director.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    public static final class Builder {

        private String id;
        private String fullName;

        private Builder(){}

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public DirectorDto build() {
            return new DirectorDto(this);
        }
    }
}

package io.inouty.jswdb.domain.movie;

import java.io.Serializable;
import java.util.Objects;

public final class ActorDto implements Serializable {

    private final String id;
    private final String fullName;

    private ActorDto(ActorDto.Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ActorDto actor) {
        return builder()
                .withId(actor.id)
                .withFullName(actor.fullName);
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
        ActorDto actor = (ActorDto) o;
        return Objects.equals(id, actor.id) &&
                Objects.equals(fullName, actor.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    public static final class Builder {
        private String id;
        private String fullName;

        private Builder() {}

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public ActorDto build() {
            return new ActorDto(this);
        }
    }

}

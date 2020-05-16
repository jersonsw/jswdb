package io.inouty.jswdb.core.domain.movie;

import java.util.Objects;

public final class Writer {

    private final String id;
    private final String fullName;

    private Writer(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Writer writer) {
        return builder()
                .withId(writer.id)
                .withFullName(writer.fullName);
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
        Writer writer = (Writer) o;
        return Objects.equals(id, writer.id) &&
                Objects.equals(fullName, writer.fullName);
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

        public Writer build() {
            return new Writer(this);
        }
    }
}

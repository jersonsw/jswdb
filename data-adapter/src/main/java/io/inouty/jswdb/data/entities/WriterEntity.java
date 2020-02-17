package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.Writer;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "writer")
@Table(schema = "works", name = "writers")
public class WriterEntity implements Mapeable<Writer> {

    @Id
    @Column(nullable = false, length = 20)
    private String id;

    @Column(name = "full_name", nullable = false, length = 60)
    private String fullName;

    public WriterEntity() {}

    private WriterEntity(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(WriterEntity writerEntity) {
        return builder()
                .withId(writerEntity.id)
                .withFullName(writerEntity.fullName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static final class Builder {

        private String id;
        private String fullName;

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public WriterEntity build() {
            return new WriterEntity(this);
        }
    }

    @Override
    public Writer to() {
        return EntityMapper.to(this);
    }
}

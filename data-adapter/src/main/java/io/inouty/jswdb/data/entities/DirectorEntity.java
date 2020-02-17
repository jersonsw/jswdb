package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.Director;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "director")
@Table(schema = "works", name = "directors")
public class DirectorEntity implements Mapeable<Director> {

    @Id
    @Column(nullable = false, length = 20)
    private String id;

    @Column(name = "full_name", nullable = false, length = 60)
    private String fullName;

    public DirectorEntity() {
    }

    private DirectorEntity(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(DirectorEntity directorEntity) {
        return builder()
                .withId(directorEntity.id)
                .withFullName(directorEntity.fullName);
    }

    public DirectorEntity(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
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

        public DirectorEntity build() {
            return new DirectorEntity(this);
        }
    }

    @Override
    public Director to() {
        return EntityMapper.to(this);
    }
}

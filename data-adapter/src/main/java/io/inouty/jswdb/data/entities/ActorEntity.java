package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.Actor;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mappable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "actor")
@Table(schema = "works", name = "actors")
public class ActorEntity implements Mappable<Actor> {

    @Id
    @Column(nullable = false, length = 20)
    private String id;

    @Column(name = "full_name", nullable = false, length = 60)
    private String fullName;

    public ActorEntity() {}

    private ActorEntity(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ActorEntity actorEntity) {
        return builder()
                .withId(actorEntity.id)
                .withFullName(actorEntity.fullName);
    }

    public ActorEntity(String id, String fullName) {
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

        private Builder() {}

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public ActorEntity build() {
            return new ActorEntity(this);
        }
    }

    @Override
    public Actor to() {
        return EntityMapper.to(this);
    }
}

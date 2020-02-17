package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.Genre;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.*;

@Entity(name = "genre")
@Table(schema = "works", name = "genres")
public class GenreEntity implements Mapeable<Genre> {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    public GenreEntity() {
    }

    private GenreEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        public GenreEntity build() {
            return new GenreEntity(this);
        }
    }

    @Override
    public Genre to() {
        return EntityMapper.to(this);
    }

}

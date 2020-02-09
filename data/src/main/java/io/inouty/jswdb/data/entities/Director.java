package io.inouty.jswdb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "works", name = "directors")
public class Director {

    @Id
    @Column(nullable = false, length = 20)
    private String id;

    @Column(name = "full_name", nullable = false, length = 60)
    private String fullName;

    public Director() {}

    private Director(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(Director director){
        return builder()
                .withId(director.id)
                .withFullName(director.fullName);
    }

    public Director(String id, String fullName) {
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

        private Builder(){}

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Director build() {
            return new Director(this);
        }
    }

}

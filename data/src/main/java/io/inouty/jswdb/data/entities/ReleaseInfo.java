package io.inouty.jswdb.data.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        schema = "works",
        name = "release_info",
        uniqueConstraints = @UniqueConstraint(
                name = "release_info_uk",
                columnNames = {"movie_id", "country_code", "note", "release_date"}
        )
)
public class ReleaseInfo {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "country_code", nullable = false, length = 20)
    private String countryCode;

    @Column(name = "country_name", nullable = false, length = 40)
    private String countryName;

    @Column(nullable = false, name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column()
    private String note;

    public ReleaseInfo() {
    }

    private ReleaseInfo(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.countryCode = builder.countryCode;
        this.countryName = builder.countryName;
        this.releaseDate = builder.releaseDate;
        this.note = builder.note;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ReleaseInfo releaseInfo) {
        return builder()
                .withId(releaseInfo.id)
                .withMovie(releaseInfo.movie)
                .withCountryCode(releaseInfo.countryCode)
                .withCountryName(releaseInfo.countryName)
                .withReleaseDate(releaseInfo.releaseDate)
                .withNote(releaseInfo.note);
    }

    public ReleaseInfo(io.inouty.jswdb.data.entities.Movie movie, String countryCode, String countryName, Date releaseDate, String note) {
        this.movie = movie;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.releaseDate = releaseDate;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public io.inouty.jswdb.data.entities.Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public static final class Builder {

        private Long id;
        private io.inouty.jswdb.data.entities.Movie movie;
        private String countryCode;
        private String countryName;
        private Date releaseDate;
        private String note;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(io.inouty.jswdb.data.entities.Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder withCountryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder withReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withNote(String note) {
            this.note = note;
            return this;
        }

        public ReleaseInfo build() {
            return new ReleaseInfo(this);
        }
    }

}

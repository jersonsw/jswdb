package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.ReleaseInfo;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mapeable;

import javax.persistence.*;
import java.util.Date;

@Entity(name="releaseInfo")
@Table(
        schema = "works",
        name = "release_info",
        uniqueConstraints = @UniqueConstraint(
                name = "release_info_uk",
                columnNames = {"movie_id", "country_code", "note", "release_date"}
        )
)
public class ReleaseInfoEntity implements Mapeable<ReleaseInfo> {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @Column(name = "country_code", nullable = false, length = 20)
    private String countryCode;

    @Column(name = "country_name", nullable = false, length = 40)
    private String countryName;

    @Column(nullable = false, name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column()
    private String note;

    public ReleaseInfoEntity() {}

    private ReleaseInfoEntity(Builder builder) {
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

    public static Builder builder(ReleaseInfoEntity releaseInfo) {
        return builder()
                .withId(releaseInfo.id)
                .withMovie(releaseInfo.movie)
                .withCountryCode(releaseInfo.countryCode)
                .withCountryName(releaseInfo.countryName)
                .withReleaseDate(releaseInfo.releaseDate)
                .withNote(releaseInfo.note);
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

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
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
        private MovieEntity movie;
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

        public Builder withMovie(MovieEntity movie) {
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

        public ReleaseInfoEntity build() {
            return new ReleaseInfoEntity(this);
        }
    }

    @Override
    public ReleaseInfo to() {
        return EntityMapper.to(this);
    }

}

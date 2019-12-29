package io.inouty.jswdb.core.entities.movie;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public final class ReleaseInfoDto implements Serializable {

    private final Long id;
    private final MovieDto movie;
    private final String countryCode;
    private final String countryName;
    private final Date releaseDate;
    private final String note;

    private ReleaseInfoDto(Builder builder) {
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

    public static Builder builder(ReleaseInfoDto releaseInfo){
        return new Builder()
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

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public String getNote() {
        return note;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReleaseInfoDto that = (ReleaseInfoDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(countryName, that.countryName) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, countryCode, countryName, releaseDate, note);
    }

    public static final class Builder {

        private Long id;
        private MovieDto movie;
        private String countryCode;
        private String countryName;
        private Date releaseDate;
        private String note;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(MovieDto movieId) {
            this.movie = movieId;
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

        public ReleaseInfoDto build() {
            return new ReleaseInfoDto(this);
        }
    }
}

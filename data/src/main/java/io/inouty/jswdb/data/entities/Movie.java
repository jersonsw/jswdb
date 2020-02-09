package io.inouty.jswdb.data.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity()
@Table(name = "movies", schema = "works")
public class Movie{

    @Id()
    @Column(length = 20)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(length = 3000)
    private String summary;

    @Column()
    private Integer year;

    @Column(length = 30)
    private String certificate;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "rating_avg")
    private Double ratingAvg;

    @Column(name = "rating_count")
    private Long ratingCount;

    @Column(name = "metascore")
    private Integer metaScore;

    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER)
    private Set<MovieGenre> moviesGenres = new HashSet<>();

    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER
    )
    private Set<ReleaseInfo> releaseInfo = new HashSet<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "movie"
    )
    private Set<MovieWriter> movieWriters = new HashSet<>();

    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER
    )
    private Set<MovieDirector> moviesDirectors = new HashSet<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "movie"
    )
    private Set<MovieActorCharacter> movieActorsCharacters = new HashSet<>();

    public Movie() {
    }

    private Movie(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.summary = builder.summary;
        this.year = builder.year;
        this.certificate = builder.certificate;
        this.profileImg = builder.profileImg;
        this.trailerUrl = builder.trailerUrl;
        this.duration = builder.duration;
        this.ratingAvg = builder.ratingAvg;
        this.ratingCount = builder.ratingCount;
        this.metaScore = builder.metaScore;
        this.moviesGenres = builder.moviesGenres;
        this.releaseInfo = builder.releaseInfo;
        this.movieWriters = builder.movieWriters;
        this.moviesDirectors = builder.moviesDirectors;
        this.movieActorsCharacters = builder.movieActorsCharacters;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(Double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }


    public Integer getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(Integer metaScore) {
        this.metaScore = metaScore;
    }


    public Set<MovieGenre> getMoviesGenres() {
        return moviesGenres;
    }

    public void setMoviesGenres(Set<MovieGenre> moviesGenres) {
        this.moviesGenres = moviesGenres;
    }

    public Set<ReleaseInfo> getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(Set<ReleaseInfo> releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public Set<MovieWriter> getMovieWriters() {
        return movieWriters;
    }

    public void setMovieWriters(Set<MovieWriter> movieWriters) {
        this.movieWriters = movieWriters;
    }

    public Set<MovieActorCharacter> getMovieActorsCharacters() {
        return movieActorsCharacters;
    }

    public void setMovieActorsCharacters(Set<MovieActorCharacter> movieActorsCharacters) {
        this.movieActorsCharacters = movieActorsCharacters;
    }

    public Set<MovieDirector> getMoviesDirectors() {
        return moviesDirectors;
    }

    public void setMoviesDirectors(Set<MovieDirector> moviesDirectors) {
        this.moviesDirectors = moviesDirectors;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", year=").append(year);
        sb.append(", certificate='").append(certificate).append('\'');
        sb.append(", profileImg='").append(profileImg).append('\'');
        sb.append(", trailerUrl='").append(trailerUrl).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", ratingAvg=").append(ratingAvg);
        sb.append(", ratingCount=").append(ratingCount);
        sb.append(", metaScore=").append(metaScore);
        sb.append(", moviesGenres=").append(moviesGenres);
        sb.append(", releaseInfo=").append(releaseInfo);
        sb.append(", movieWriters=").append(movieWriters);
        sb.append(", moviesDirectors=").append(moviesDirectors);
        sb.append(", movieActorsCharacters=").append(movieActorsCharacters);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(summary, movie.summary) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(certificate, movie.certificate) &&
                Objects.equals(profileImg, movie.profileImg) &&
                Objects.equals(trailerUrl, movie.trailerUrl) &&
                Objects.equals(duration, movie.duration) &&
                Objects.equals(ratingAvg, movie.ratingAvg) &&
                Objects.equals(ratingCount, movie.ratingCount) &&
                Objects.equals(metaScore, movie.metaScore) &&
                Objects.equals(moviesGenres, movie.moviesGenres) &&
                Objects.equals(releaseInfo, movie.releaseInfo) &&
                Objects.equals(movieWriters, movie.movieWriters) &&
                Objects.equals(moviesDirectors, movie.moviesDirectors) &&
                Objects.equals(movieActorsCharacters, movie.movieActorsCharacters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, year, certificate, profileImg, trailerUrl, duration, ratingAvg, ratingCount, metaScore, moviesGenres, releaseInfo, movieWriters, moviesDirectors, movieActorsCharacters);
    }


    public static final class Builder {
        private String id;
        private String title;
        private String summary;
        private Integer year;
        private String certificate;
        private String profileImg;
        private String trailerUrl;
        private Time duration;
        private Double ratingAvg;
        private Long ratingCount;
        private Integer metaScore;
        private Set<MovieGenre> moviesGenres = new HashSet<>();
        private Set<ReleaseInfo> releaseInfo = new HashSet<>();
        private Set<MovieWriter> movieWriters = new HashSet<>();
        private Set<MovieDirector> moviesDirectors = new HashSet<>();
        private Set<MovieActorCharacter> movieActorsCharacters = new HashSet<>();

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder withYear(Integer year) {
            this.year = year;
            return this;
        }

        public Builder withCertificate(String certificate) {
            this.certificate = certificate;
            return this;
        }

        public Builder withProfileImg(String profileImg) {
            this.profileImg = profileImg;
            return this;
        }

        public Builder withTrailerUrl(String trailerUrl) {
            this.trailerUrl = trailerUrl;
            return this;
        }

        public Builder withDuration(Time duration) {
            this.duration = duration;
            return this;
        }

        public Builder withRatingAvg(Double ratingAvg) {
            this.ratingAvg = ratingAvg;
            return this;
        }

        public Builder withRatingCount(Long ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public Builder withMetaScore(Integer metaScore) {
            this.metaScore = metaScore;
            return this;
        }

        public Builder withMoviesGenres(Set<MovieGenre> moviesGenres) {
            this.moviesGenres = moviesGenres;
            return this;
        }

        public Builder withReleaseInfo(Set<ReleaseInfo> releaseInfo) {
            this.releaseInfo = releaseInfo;
            return this;
        }

        public Builder withMovieWriters(Set<MovieWriter> movieWriters) {
            this.movieWriters = movieWriters;
            return this;
        }

        public Builder withMoviesDirectors(Set<MovieDirector> moviesDirectors) {
            this.moviesDirectors = moviesDirectors;
            return this;
        }

        public Builder withMovieActorsCharacters(Set<MovieActorCharacter> movieActorsCharacters) {
            this.movieActorsCharacters = movieActorsCharacters;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}

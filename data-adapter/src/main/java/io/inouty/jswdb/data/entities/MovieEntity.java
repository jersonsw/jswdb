package io.inouty.jswdb.data.entities;

import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.domain.movie.MovieWriter;
import io.inouty.jswdb.data.mappers.EntityMapper;
import io.inouty.jswdb.data.mappers.Mappable;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "movie")
@Table(name = "movies", schema = "works")
public class MovieEntity implements Mappable<Movie> {

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
    private Set<MovieGenreEntity> movieGenres = new HashSet<MovieGenreEntity>();

    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER
    )
    private Set<ReleaseInfoEntity> releaseInfo = new HashSet<ReleaseInfoEntity>();

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "movie"
    )
    private Set<MovieWriterEntity> movieWriters = new HashSet<MovieWriterEntity>();

    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER
    )
    private Set<MovieDirectorEntity> movieDirectors = new HashSet<MovieDirectorEntity>();

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "movie"
    )
    private Set<MovieActorCharacterEntity> movieActorsCharacters = new HashSet<MovieActorCharacterEntity>();

    public MovieEntity() {
    }

    private MovieEntity(Builder builder) {
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
        this.movieGenres = builder.movieGenres;
        this.releaseInfo = builder.releaseInfo;
        this.movieWriters = builder.movieWriters;
        this.movieDirectors = builder.movieDirectors;
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


    public Set<MovieGenreEntity> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Set<MovieGenreEntity> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public Set<ReleaseInfoEntity> getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(Set<ReleaseInfoEntity> releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public Set<MovieWriterEntity> getMovieWriters() {
        return movieWriters;
    }

    public void setMovieWriters(Set<MovieWriterEntity> movieWriters) {
        this.movieWriters = movieWriters;
    }

    public Set<MovieActorCharacterEntity> getMovieActorsCharacters() {
        return movieActorsCharacters;
    }

    public void setMovieActorsCharacters(Set<MovieActorCharacterEntity> movieActorsCharacters) {
        this.movieActorsCharacters = movieActorsCharacters;
    }

    public Set<MovieDirectorEntity> getMovieDirectors() {
        return movieDirectors;
    }

    public void setMovieDirectors(Set<MovieDirectorEntity> movieDirectors) {
        this.movieDirectors = movieDirectors;
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
        sb.append(", moviesGenres=").append(movieGenres);
        sb.append(", releaseInfo=").append(releaseInfo);
        sb.append(", movieWriters=").append(movieWriters);
        sb.append(", moviesDirectors=").append(movieDirectors);
        sb.append(", movieActorsCharacters=").append(movieActorsCharacters);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity movieEntity = (MovieEntity) o;
        return Objects.equals(id, movieEntity.id) &&
                Objects.equals(title, movieEntity.title) &&
                Objects.equals(summary, movieEntity.summary) &&
                Objects.equals(year, movieEntity.year) &&
                Objects.equals(certificate, movieEntity.certificate) &&
                Objects.equals(profileImg, movieEntity.profileImg) &&
                Objects.equals(trailerUrl, movieEntity.trailerUrl) &&
                Objects.equals(duration, movieEntity.duration) &&
                Objects.equals(ratingAvg, movieEntity.ratingAvg) &&
                Objects.equals(ratingCount, movieEntity.ratingCount) &&
                Objects.equals(metaScore, movieEntity.metaScore) &&
                Objects.equals(movieGenres, movieEntity.movieGenres) &&
                Objects.equals(releaseInfo, movieEntity.releaseInfo) &&
                Objects.equals(movieWriters, movieEntity.movieWriters) &&
                Objects.equals(movieDirectors, movieEntity.movieDirectors) &&
                Objects.equals(movieActorsCharacters, movieEntity.movieActorsCharacters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, year, certificate, profileImg, trailerUrl, duration, ratingAvg, ratingCount, metaScore, movieGenres, releaseInfo, movieWriters, movieDirectors, movieActorsCharacters);
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
        private Set<MovieGenreEntity> movieGenres = new HashSet<MovieGenreEntity>();
        private Set<ReleaseInfoEntity> releaseInfo = new HashSet<ReleaseInfoEntity>();
        private Set<MovieWriterEntity> movieWriters = new HashSet<MovieWriterEntity>();
        private Set<MovieDirectorEntity> movieDirectors = new HashSet<MovieDirectorEntity>();
        private Set<MovieActorCharacterEntity> movieActorsCharacters = new HashSet<MovieActorCharacterEntity>();

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

        public Builder withMoviesGenres(Set<MovieGenreEntity> movieGenres) {
            this.movieGenres = movieGenres;
            return this;
        }

        public Builder withReleaseInfo(Set<ReleaseInfoEntity> releaseInfo) {
            this.releaseInfo = releaseInfo;
            return this;
        }

        public Builder withMovieWriters(Set<MovieWriterEntity> movieWriters) {
            this.movieWriters = movieWriters;
            return this;
        }

        public Builder withMoviesDirectors(Set<MovieDirectorEntity> moviesDirectors) {
            this.movieDirectors = moviesDirectors;
            return this;
        }

        public Builder withMovieActorsCharacters(Set<MovieActorCharacterEntity> movieActorsCharacters) {
            this.movieActorsCharacters = movieActorsCharacters;
            return this;
        }

        public MovieEntity build() {
            return new MovieEntity(this);
        }
    }

    @Override
    public Movie to() {
        return EntityMapper.to(this);
    }
}

package io.inouty.jswdb.domain.movie;

import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class MovieDto implements Serializable {

    private final String id;
    private final String title;
    private final String summary;
    private final Integer year;
    private final String certificate;
    private final String profileImg;
    private final String trailerUrl;
    private final Time duration;
    private final Double ratingAvg;
    private final Long ratingCount;
    private final Integer metaScore;
    private final Set<MovieGenreDto> moviesGenres;
    private final Set<ReleaseInfoDto> releaseInfo;
    private final Set<MovieWriterDto> movieWriters;
    private final Set<MovieDirectorDto> moviesDirectors;
    private final Set<MovieActorCharacterDto> movieActorsCharacters;

    private MovieDto(Builder builder) {
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

    public static Builder builder(MovieDto movie) {
        return builder()
                .withId(movie.id)
                .withTitle(movie.title)
                .withSummary(movie.summary)
                .withYear(movie.year)
                .withCertificate(movie.certificate)
                .withProfileImg(movie.profileImg)
                .withTrailerUrl(movie.trailerUrl)
                .withDuration(movie.duration)
                .withRatingAvg(movie.ratingAvg)
                .withRatingCount(movie.ratingCount)
                .withMetaScore(movie.metaScore)
                .withMoviesGenres(movie.getMoviesGenres())
                .withReleaseInfo(movie.getReleaseInfo())
                .withMovieWriters(movie.getMovieWriters())
                .withMoviesDirectors(movie.getMoviesDirectors())
                .withMovieActorsCharacters(movie.getMovieActorsCharacters());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public Integer getYear() {
        return year;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public Time getDuration() {
        return duration;
    }

    public Double getRatingAvg() {
        return ratingAvg;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public Integer getMetaScore() {
        return metaScore;
    }

    public Set<MovieGenreDto> getMoviesGenres() {
        return moviesGenres;
    }

    public Set<ReleaseInfoDto> getReleaseInfo() {
        return releaseInfo;
    }

    public Set<MovieWriterDto> getMovieWriters() {
        return movieWriters;
    }

    public Set<MovieDirectorDto> getMoviesDirectors() {
        return moviesDirectors;
    }

    public Set<MovieActorCharacterDto> getMovieActorsCharacters() {
        return movieActorsCharacters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movie = (MovieDto) o;
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
        private Set<MovieGenreDto> moviesGenres = new HashSet<>();
        private Set<ReleaseInfoDto> releaseInfo = new HashSet<>();
        private Set<MovieWriterDto> movieWriters = new HashSet<>();
        private Set<MovieDirectorDto> moviesDirectors = new HashSet<>();
        private Set<MovieActorCharacterDto> movieActorsCharacters = new HashSet<>();

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

        public Builder withMoviesGenres(Set<MovieGenreDto> moviesGenres) {
            this.moviesGenres = moviesGenres;
            return this;
        }

        public Builder withReleaseInfo(Set<ReleaseInfoDto> releaseInfo) {
            this.releaseInfo = releaseInfo;
            return this;
        }

        public Builder withMovieWriters(Set<MovieWriterDto> movieWriters) {
            this.movieWriters = movieWriters;
            return this;
        }

        public Builder withMoviesDirectors(Set<MovieDirectorDto> moviesDirectors) {
            this.moviesDirectors = moviesDirectors;
            return this;
        }

        public Builder withMovieActorsCharacters(Set<MovieActorCharacterDto> movieActorsCharacters) {
            this.movieActorsCharacters = movieActorsCharacters;
            return this;
        }

        public MovieDto build() {
            return new MovieDto(this);
        }
    }
}

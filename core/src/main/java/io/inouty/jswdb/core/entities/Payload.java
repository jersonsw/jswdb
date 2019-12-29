package io.inouty.jswdb.core.entities;

import io.inouty.jswdb.core.entities.movie.*;

import java.util.*;

public final class Payload {

    private final MovieDto movie;
    private final Map<ActorDto, CharacterDto> actorsCharacters;
    private final Set<DirectorDto> directors;
    private final Set<GenreDto> genres;
    private final Map<WriterDto, String> writersCredits;
    private final Set<ReleaseInfoDto> releaseInfos;

    private Payload(Builder builder) {
        this.movie = builder.movie;
        this.actorsCharacters = builder.actorsCharacters;
        this.directors = builder.directors;
        this.genres = builder.genres;
        this.writersCredits = builder.writersCredits;
        this.releaseInfos = builder.releaseInfos;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Payload payload) {
        return builder()
                .withMovie(payload.movie)
                .withActorsCharacters(payload.actorsCharacters)
                .withDirectors(payload.directors)
                .withGenres(payload.genres)
                .withWritersCredits(payload.writersCredits)
                .withReleaseInfo(payload.releaseInfos);
    }

    public MovieDto getMovie() {
        return movie;
    }

    public Map<ActorDto, CharacterDto> getActorsCharacters() {
        return actorsCharacters;
    }

    public Set<DirectorDto> getDirectors() {
        return directors;
    }

    public Set<GenreDto> getGenres() {
        return genres;
    }

    public Map<WriterDto, String> getWritersCredits() {
        return writersCredits;
    }

    public Set<ReleaseInfoDto> getReleaseInfos() {
        return releaseInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payload payload = (Payload) o;
        return Objects.equals(movie, payload.movie) &&
                Objects.equals(actorsCharacters, payload.actorsCharacters) &&
                Objects.equals(directors, payload.directors) &&
                Objects.equals(genres, payload.genres) &&
                Objects.equals(writersCredits, payload.writersCredits) &&
                Objects.equals(releaseInfos, payload.releaseInfos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, actorsCharacters, directors, genres, writersCredits, releaseInfos);
    }

    public static final class Builder {

        private MovieDto movie;
        private Map<ActorDto, CharacterDto> actorsCharacters = new HashMap<>();
        private Set<DirectorDto> directors = new HashSet<>();
        private Set<GenreDto> genres = new HashSet<>();
        private Map<WriterDto, String> writersCredits = new HashMap<>();
        private Set<ReleaseInfoDto> releaseInfos = new HashSet<>();

        private Builder() {
        }

        public Builder withMovie(MovieDto movie) {
            this.movie = movie;
            return this;
        }

        public Builder withActorsCharacters(Map<ActorDto, CharacterDto> actorsCharacters) {
            this.actorsCharacters = actorsCharacters;
            return this;
        }

        public Builder withDirectors(Set<DirectorDto> directors) {
            this.directors = directors;
            return this;
        }

        public Builder withGenres(Set<GenreDto> genres) {
            this.genres = genres;
            return this;
        }

        public Builder withWritersCredits(Map<WriterDto, String> writersCredits) {
            this.writersCredits = writersCredits;
            return this;
        }

        public Builder withReleaseInfo(Set<ReleaseInfoDto> releaseInfos) {
            this.releaseInfos = releaseInfos;
            return this;
        }

        public Payload build() {
            return new Payload(this);
        }

    }

}

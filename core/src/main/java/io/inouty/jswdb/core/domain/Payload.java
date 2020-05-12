package io.inouty.jswdb.core.domain;

import io.inouty.jswdb.core.domain.movie.Character;
import io.inouty.jswdb.core.domain.movie.*;

import java.util.*;

public final class Payload {

    private final Movie movie;
    private final Map<Actor, Character> actorsCharacters;
    private final Set<Director> directors;
    private final Set<Genre> genres;
    private final Map<Writer, String> writersCredits;
    private final Set<ReleaseInfo> releaseInfos;

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

    public Movie getMovie() {
        return movie;
    }

    public Map<Actor, Character> getActorsCharacters() {
        return actorsCharacters;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Map<Writer, String> getWritersCredits() {
        return writersCredits;
    }

    public Set<ReleaseInfo> getReleaseInfos() {
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

        private Movie movie;
        private Map<Actor, Character> actorsCharacters = new HashMap<Actor, Character>();
        private Set<Director> directors = new HashSet<Director>();
        private Set<Genre> genres = new HashSet<Genre>();
        private Map<Writer, String> writersCredits = new HashMap<Writer,String>();
        private Set<ReleaseInfo> releaseInfos = new HashSet<ReleaseInfo>();

        private Builder() {
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withActorsCharacters(Map<Actor, Character> actorsCharacters) {
            this.actorsCharacters = actorsCharacters;
            return this;
        }

        public Builder withDirectors(Set<Director> directors) {
            this.directors = directors;
            return this;
        }

        public Builder withGenres(Set<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public Builder withWritersCredits(Map<Writer, String> writersCredits) {
            this.writersCredits = writersCredits;
            return this;
        }

        public Builder withReleaseInfo(Set<ReleaseInfo> releaseInfos) {
            this.releaseInfos = releaseInfos;
            return this;
        }

        public Payload build() {
            return new Payload(this);
        }

    }

}

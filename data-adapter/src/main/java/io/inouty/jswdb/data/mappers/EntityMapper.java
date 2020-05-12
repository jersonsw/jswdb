package io.inouty.jswdb.data.mappers;

import io.inouty.jswdb.core.domain.movie.Character;
import io.inouty.jswdb.core.domain.movie.*;
import io.inouty.jswdb.data.entities.*;

import java.util.stream.Collectors;

public class EntityMapper{

    public static GenreEntity from(Genre genre) {
        return GenreEntity.builder()
                .withId(genre.getId())
                .withName(genre.getName())
                .build();
    }

    public static Genre to(GenreEntity genreEntity) {
        return Genre
                .builder()
                .withId(genreEntity.getId())
                .withName(genreEntity.getName())
                .build();
    }


    public static ActorEntity from(Actor actor) {
        return ActorEntity
                .builder()
                .withId(actor.getId())
                .withFullName(actor.getFullName())
                .build();
    }

    public static Actor to(ActorEntity actorEntity) {
        return Actor
                .builder()
                .withId(actorEntity.getId())
                .withFullName(actorEntity.getFullName())
                .build();
    }

    public static CharacterEntity from(Character character) {
        return CharacterEntity
                .builder()
                .withId(character.getId())
                .withImdbId(character.getImdbId())
                .withName(character.getName())
                .build();
    }

    public static Character to(CharacterEntity characterEntity) {
        return Character
                .builder()
                .withId(characterEntity.getId())
                .withImdbId(characterEntity.getImdbId())
                .withName(characterEntity.getName())
                .build();
    }

    public static WriterEntity from(Writer writer) {
        return WriterEntity
                .builder()
                .withId(writer.getId())
                .withFullName(writer.getFullName())
                .build();
    }

    public static Writer to(WriterEntity writerEntity) {
        return Writer
                .builder()
                .withId(writerEntity.getId())
                .withFullName(writerEntity.getFullName())
                .build();
    }

    public static DirectorEntity from(Director director) {
        return DirectorEntity
                .builder()
                .withId(director.getId())
                .withFullName(director.getFullName())
                .build();
    }

    public static Director to(DirectorEntity directorEntity) {
        return Director
                .builder()
                .withId(directorEntity.getId())
                .withFullName(directorEntity.getFullName())
                .build();
    }

    public static MovieGenreIdEntity from(MovieGenreId dto) {
        return MovieGenreIdEntity
                .builder()
                .withMovieId(dto.getMovieId())
                .withGenreId(dto.getGenreId())
                .build();
    }

    public static MovieGenreId to(MovieGenreIdEntity id) {
        return MovieGenreId
                .builder()
                .withMovieId(id.getMovieId())
                .withGenreId(id.getGenreId())
                .build();
    }

    public static MovieGenreEntity from(MovieGenre mg) {
        return MovieGenreEntity
                .builder()
                .withId(from(mg.getId()))
                .build();
    }

    public static MovieGenre to(MovieGenreEntity movieGenreEntity) {
        return MovieGenre
                .builder()
                .withId(to(movieGenreEntity.getId()))
                .build();
    }

    public static ReleaseInfoEntity from(ReleaseInfo dto) {
        return ReleaseInfoEntity
                .builder()
                .withId(dto.getId())
                .withCountryCode(dto.getCountryCode())
                .withCountryName(dto.getCountryName())
                .withReleaseDate(dto.getReleaseDate())
                .withNote(dto.getNote())
                .build();
    }

    public static ReleaseInfo to(ReleaseInfoEntity releaseInfoEntity) {
        return ReleaseInfo
                .builder()
                .withId(releaseInfoEntity.getId())
                .withCountryCode(releaseInfoEntity.getCountryCode())
                .withCountryName(releaseInfoEntity.getCountryName())
                .withReleaseDate(releaseInfoEntity.getReleaseDate())
                .withNote(releaseInfoEntity.getNote())
                .build();
    }


    public static MovieWriterIdEntity from(MovieWriterId dto) {
        return MovieWriterIdEntity
                .builder()
                .withMovieId(dto.getMovieId())
                .withWriterId(dto.getWriterId())
                .build();
    }

    public static MovieWriterId to(MovieWriterIdEntity id) {
        return MovieWriterId
                .builder()
                .withMovieId(id.getMovieId())
                .withWriterId(id.getWriterId())
                .build();
    }

    public static MovieWriterEntity from(MovieWriter dto) {
        return MovieWriterEntity
                .builder()
                .withId(from(dto.getId()))
                .withCredits(dto.getCredits())
                .build();
    }


    public static MovieWriter to(MovieWriterEntity mw) {
        return MovieWriter
                .builder()
                .withId(to(mw.getId()))
                .withCredits(mw.getCredits())
                .build();
    }


    public static MovieDirectorIdEntity from(MovieDirectorId dto) {
        return MovieDirectorIdEntity
                .builder()
                .withMovieId(dto.getMovieId())
                .withDirectorId(dto.getDirectorId())
                .build();
    }

    public static MovieDirectorId to(MovieDirectorIdEntity id) {
        return MovieDirectorId
                .builder()
                .withMovieId(id.getMovieId())
                .withDirectorId(id.getDirectorId())
                .build();
    }

    public static MovieDirectorEntity from(MovieDirector dto) {
        return MovieDirectorEntity
                .builder()
                .withId(from(dto.getId()))
                .build();
    }

    public static MovieDirector to(MovieDirectorEntity md) {
        return MovieDirector
                .builder()
                .withId(to(md.getId()))
                .build();
    }

    public static MovieActorCharacterIdEntity from(MovieActorCharacterId dto) {
        return MovieActorCharacterIdEntity
                .builder()
                .withMovieId(dto.getMovieId())
                .withActorId(dto.getActorId())
                .withCharacterId(dto.getCharacterId())
                .build();
    }

    public static MovieActorCharacterId to(MovieActorCharacterIdEntity id) {
        return MovieActorCharacterId
                .builder()
                .withMovieId(id.getMovieId())
                .withActorId(id.getActorId())
                .withCharacterId(id.getCharacterId())
                .build();
    }

    public static MovieActorCharacterEntity from(MovieActorCharacter dto) {
        return MovieActorCharacterEntity
                .builder()
                .withId(from(dto.getId()))
                .build();
    }

    public static MovieActorCharacter to(MovieActorCharacterEntity mac) {
        return MovieActorCharacter
                .builder()
                .withId(to(mac.getId()))
                .build();
    }

    public static MovieEntity from(Movie movie) {
        return MovieEntity
                .builder()
                .withId(movie.getId())
                .withTitle(movie.getTitle())
                .withSummary(movie.getSummary())
                .withYear(movie.getYear())
                .withCertificate(movie.getCertificate())
                .withProfileImg(movie.getProfileImg())
                .withTrailerUrl(movie.getTrailerUrl())
                .withDuration(movie.getDuration())
                .withRatingAvg(movie.getRatingAvg())
                .withRatingCount(movie.getRatingCount())
                .withMetaScore(movie.getMetaScore())
                .withMoviesGenres(movie.getMoviesGenres().stream().map(EntityMapper::from).collect(Collectors.toSet()))
                .withReleaseInfo(movie.getReleaseInfo().stream().map(EntityMapper::from).collect(Collectors.toSet()))
                .withMovieWriters(movie.getMovieWriters().stream().map(EntityMapper::from).collect(Collectors.toSet()))
                .withMoviesDirectors(movie.getMoviesDirectors().stream().map(EntityMapper::from).collect(Collectors.toSet()))
                .withMovieActorsCharacters(movie.getMovieActorsCharacters().stream().map(EntityMapper::from).collect(Collectors.toSet()))
                .build();
    }

    public static Movie to(MovieEntity movieEntity) {
        return Movie
                .builder()
                .withId(movieEntity.getId())
                .withTitle(movieEntity.getTitle())
                .withSummary(movieEntity.getSummary())
                .withYear(movieEntity.getYear())
                .withCertificate(movieEntity.getCertificate())
                .withProfileImg(movieEntity.getProfileImg())
                .withTrailerUrl(movieEntity.getTrailerUrl())
                .withDuration(movieEntity.getDuration())
                .withRatingAvg(movieEntity.getRatingAvg())
                .withRatingCount(movieEntity.getRatingCount())
                .withMetaScore(movieEntity.getMetaScore())
                .withMoviesGenres(movieEntity.getMovieGenres().stream().map(EntityMapper::to).collect(Collectors.toSet()))
                .withReleaseInfo(movieEntity.getReleaseInfo().stream().map(EntityMapper::to).collect(Collectors.toSet()))
                .withMovieWriters(movieEntity.getMovieWriters().stream().map(EntityMapper::to).collect(Collectors.toSet()))
                .withMoviesDirectors(movieEntity.getMovieDirectors().stream().map(EntityMapper::to).collect(Collectors.toSet()))
                .withMovieActorsCharacters(movieEntity.getMovieActorsCharacters().stream().map(EntityMapper::to).collect(Collectors.toSet()))
                .build();
    }

}

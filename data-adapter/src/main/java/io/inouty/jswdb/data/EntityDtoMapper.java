package io.inouty.jswdb.data;

import io.inouty.jswdb.core.domain.*;
import io.inouty.jswdb.data.entities.*;
import io.inouty.jswdb.data.entities.Character;
import java.util.stream.Collectors;

public class EntityDtoMapper {

    public static Genre toGenre(GenreDto genreDto) {
        return Genre
                .builder()
                .withId(genreDto.getId())
                .withName(genreDto.getName())
                .build();
    }

    public static GenreDto fromGenre(Genre genre) {
        return GenreDto
                .builder()
                .withId(genre.getId())
                .withName(genre.getName())
                .build();
    }


    public static Actor toActor(ActorDto actorDto) {
        return Actor
                .builder()
                .withId(actorDto.getId())
                .withFullName(actorDto.getFullName())
                .build();
    }

    public static ActorDto fromActor(Actor actor) {
        return ActorDto
                .builder()
                .withId(actor.getId())
                .withFullName(actor.getFullName())
                .build();
    }

    public static Character toCharacter(CharacterDto characterDto) {
        return Character
                .builder()
                .withId(characterDto.getId())
                .withImdbId(characterDto.getImdbId())
                .withName(characterDto.getName())
                .build();
    }

    public static CharacterDto fromCharacter(Character character) {
        return CharacterDto
                .builder()
                .withId(character.getId())
                .withImdbId(character.getImdbId())
                .withName(character.getName())
                .build();
    }

    public static Writer toWriter(WriterDto writerDto) {
        return Writer
                .builder()
                .withId(writerDto.getId())
                .withFullName(writerDto.getFullName())
                .build();
    }

    public static WriterDto fromWriter(Writer writer) {
        return WriterDto
                .builder()
                .withId(writer.getId())
                .withFullName(writer.getFullName())
                .build();
    }

    public static Director toDirector(DirectorDto directorDto) {
        return Director
                .builder()
                .withId(directorDto.getId())
                .withFullName(directorDto.getFullName())
                .build();
    }

    public static DirectorDto fromDirector(Director director) {
        return DirectorDto
                .builder()
                .withId(director.getId())
                .withFullName(director.getFullName())
                .build();
    }

    public static MovieGenreId toMovieGenreId(MovieGenreIdDto dto) {
        return MovieGenreId
                .builder()
                .withMovieId(dto.getMovieId())
                .withGenreId(dto.getGenreId())
                .build();
    }

    public static MovieGenreIdDto fromMovieGenreId(MovieGenreId id) {
        return MovieGenreIdDto
                .builder()
                .withMovieId(id.getMovieId())
                .withGenreId(id.getGenreId())
                .build();
    }

    public static MovieGenre toMovieGenre(MovieGenreDto mg) {
        return MovieGenre
                .builder()
                .withId(toMovieGenreId(mg.getId()))
                .build();
    }

    public static MovieGenreDto fromMovieGenre(MovieGenre movieGenre) {
        return MovieGenreDto
                .builder()
                .withId(fromMovieGenreId(movieGenre.getId()))
                .build();
    }

    public static ReleaseInfo toReleaseInfo(ReleaseInfoDto dto) {
        return ReleaseInfo
                .builder()
                .withId(dto.getId())
                .withCountryCode(dto.getCountryCode())
                .withCountryName(dto.getCountryName())
                .withReleaseDate(dto.getReleaseDate())
                .withNote(dto.getNote())
                .build();
    }

    public static ReleaseInfoDto fromReleaseInfo(ReleaseInfo releaseInfo) {
        return ReleaseInfoDto
                .builder()
                .withId(releaseInfo.getId())
                .withCountryCode(releaseInfo.getCountryCode())
                .withCountryName(releaseInfo.getCountryName())
                .withReleaseDate(releaseInfo.getReleaseDate())
                .withNote(releaseInfo.getNote())
                .build();
    }


    public static MovieWriterId toMovieWriterId(MovieWriterIdDto dto) {
        return MovieWriterId
                .builder()
                .withMovieId(dto.getMovieId())
                .withWriterId(dto.getWriterId())
                .build();
    }

    public static MovieWriterIdDto fromMovieWriterId(MovieWriterId id) {
        return MovieWriterIdDto
                .builder()
                .withMovieId(id.getMovieId())
                .withWriterId(id.getWriterId())
                .build();
    }

    public static MovieWriter toMovieWriter(MovieWriterDto dto) {
        return MovieWriter
                .builder()
                .withId(toMovieWriterId(dto.getId()))
                .withCredits(dto.getCredits())
                .build();
    }


    public static MovieWriterDto fromMovieWriter(MovieWriter mw) {
        return MovieWriterDto
                .builder()
                .withId(fromMovieWriterId(mw.getId()))
                .withCredits(mw.getCredits())
                .build();
    }


    public static MovieDirectorId toMovieDirectorId(MovieDirectorIdDto dto) {
        return MovieDirectorId
                .builder()
                .withMovieId(dto.getMovieId())
                .withDirectorId(dto.getDirectorId())
                .build();
    }

    public static MovieDirectorIdDto fromMovieDirectorId(MovieDirectorId id) {
        return MovieDirectorIdDto
                .builder()
                .withMovieId(id.getMovieId())
                .withDirectorId(id.getDirectorId())
                .build();
    }

    public static MovieDirector toMovieDirector(MovieDirectorDto dto) {
        return MovieDirector
                .builder()
                .withId(toMovieDirectorId(dto.getId()))
                .build();
    }

    public static MovieDirectorDto fromMovieDirector(MovieDirector md) {
        return MovieDirectorDto
                .builder()
                .withId(fromMovieDirectorId(md.getId()))
                .build();
    }

    public static MovieActorCharacterId toMovieActorCharacterId(MovieActorCharacterIdDto dto) {
        return MovieActorCharacterId
                .builder()
                .withMovieId(dto.getMovieId())
                .withActorId(dto.getActorId())
                .withCharacterId(dto.getCharacterId())
                .build();
    }

    public static MovieActorCharacterIdDto fromMovieActorCharacterId(MovieActorCharacterId id) {
        return MovieActorCharacterIdDto
                .builder()
                .withMovieId(id.getMovieId())
                .withActorId(id.getActorId())
                .withCharacterId(id.getCharacterId())
                .build();
    }

    public static MovieActorCharacter toMovieActorCharacter(MovieActorCharacterDto dto) {
        return MovieActorCharacter
                .builder()
                .withId(toMovieActorCharacterId(dto.getId()))
                .build();
    }

    public static MovieActorCharacterDto fromMovieActorCharacter(MovieActorCharacter mac) {
        return MovieActorCharacterDto
                .builder()
                .withId(fromMovieActorCharacterId(mac.getId()))
                .build();
    }

    public static Movie toMovie(MovieDto movie) {
        return Movie
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
                .withMoviesGenres(movie.getMoviesGenres().stream().map(EntityDtoMapper::toMovieGenre).collect(Collectors.toSet()))
                .withReleaseInfo(movie.getReleaseInfo().stream().map(EntityDtoMapper::toReleaseInfo).collect(Collectors.toSet()))
                .withMovieWriters(movie.getMovieWriters().stream().map(EntityDtoMapper::toMovieWriter).collect(Collectors.toSet()))
                .withMoviesDirectors(movie.getMoviesDirectors().stream().map(EntityDtoMapper::toMovieDirector).collect(Collectors.toSet()))
                .withMovieActorsCharacters(movie.getMovieActorsCharacters().stream().map(EntityDtoMapper::toMovieActorCharacter).collect(Collectors.toSet()))
                .build();
    }

    public static MovieDto fromMovie(Movie movie) {
        return MovieDto
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
                .withMoviesGenres(movie.getMoviesGenres().stream().map(EntityDtoMapper::fromMovieGenre).collect(Collectors.toSet()))
                .withReleaseInfo(movie.getReleaseInfo().stream().map(EntityDtoMapper::fromReleaseInfo).collect(Collectors.toSet()))
                .withMovieWriters(movie.getMovieWriters().stream().map(EntityDtoMapper::fromMovieWriter).collect(Collectors.toSet()))
                .withMoviesDirectors(movie.getMoviesDirectors().stream().map(EntityDtoMapper::fromMovieDirector).collect(Collectors.toSet()))
                .withMovieActorsCharacters(movie.getMovieActorsCharacters().stream().map(EntityDtoMapper::fromMovieActorCharacter).collect(Collectors.toSet()))
                .build();
    }

}

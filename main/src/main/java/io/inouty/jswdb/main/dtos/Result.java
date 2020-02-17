package io.inouty.jswdb.main.dtos;

import io.inouty.jswdb.core.domain.movie.Movie;

import java.util.List;

public class Result {

    private Integer startRecord;
    private Integer endRecord;
    private Integer expectedCount;
    private List<Movie> movies;

    public Result() {
    }

    public Result(Integer startRecord, Integer endRecord, Integer expectedCount, List<Movie> movies) {
        this.startRecord = startRecord;
        this.endRecord = endRecord;
        this.expectedCount = expectedCount;
        this.movies = movies;
    }

    public Integer getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    public Integer getEndRecord() {
        return endRecord;
    }

    public void setEndRecord(Integer endRecord) {
        this.endRecord = endRecord;
    }

    public Integer getExpectedCount() {
        return expectedCount;
    }

    public void setExpectedCount(Integer expectedCount) {
        this.expectedCount = expectedCount;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

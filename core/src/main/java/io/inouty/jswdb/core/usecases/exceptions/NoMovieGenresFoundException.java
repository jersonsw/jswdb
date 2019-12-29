package io.inouty.jswdb.core.usecases.exceptions;

public class NoMovieGenresFoundException extends RuntimeException implements Skippable {

    public NoMovieGenresFoundException(String message) {
        super(message);
    }

}

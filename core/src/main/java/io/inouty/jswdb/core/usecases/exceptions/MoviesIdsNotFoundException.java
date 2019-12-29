package io.inouty.jswdb.core.usecases.exceptions;

public class MoviesIdsNotFoundException extends RuntimeException {
    public MoviesIdsNotFoundException(String message) {
        super(message);
    }
}

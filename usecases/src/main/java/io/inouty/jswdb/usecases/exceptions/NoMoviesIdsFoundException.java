package io.inouty.jswdb.usecases.exceptions;

public class NoMoviesIdsFoundException extends RuntimeException {
    public NoMoviesIdsFoundException(String message) {
        super(message);
    }
}

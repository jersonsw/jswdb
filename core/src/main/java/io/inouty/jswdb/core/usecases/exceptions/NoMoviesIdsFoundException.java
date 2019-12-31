package io.inouty.jswdb.core.usecases.exceptions;

public class NoMoviesIdsFoundException extends RuntimeException {
    public NoMoviesIdsFoundException(String message) {
        super(message);
    }
}

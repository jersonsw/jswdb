package io.inouty.jswdb.core.usecases.exceptions;

public class NoGenresFoundException extends RuntimeException {

    public NoGenresFoundException(String message) {
        super(message);
    }
}

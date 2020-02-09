package io.inouty.jswdb.usecases.exceptions;

public class NoGenresFoundException extends RuntimeException {

    public NoGenresFoundException(String message) {
        super(message);
    }
}

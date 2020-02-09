package io.inouty.jswdb.usecases.exceptions;

public class NoMorePagesException extends RuntimeException {

    public NoMorePagesException(String message) {
        super(message);
    }
}

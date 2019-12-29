package io.inouty.jswdb.core.usecases.exceptions;

public class NoMorePagesException extends RuntimeException {

    public NoMorePagesException(String message) {
        super(message);
    }
}

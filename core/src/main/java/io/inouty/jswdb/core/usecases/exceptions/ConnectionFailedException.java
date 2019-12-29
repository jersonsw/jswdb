package io.inouty.jswdb.core.usecases.exceptions;

public class ConnectionFailedException extends RuntimeException implements Skippable {

    public ConnectionFailedException(String message) {
        super(message);
    }

}

package io.inouty.jswdb.usecases.exceptions;

public class ConnectionFailedException extends RuntimeException implements Skippable {

    public ConnectionFailedException(String message) {
        super(message);
    }

}

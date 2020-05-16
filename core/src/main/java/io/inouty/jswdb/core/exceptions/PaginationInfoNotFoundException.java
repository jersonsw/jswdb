package io.inouty.jswdb.core.exceptions;

public class PaginationInfoNotFoundException extends RuntimeException implements Skippable {

    public PaginationInfoNotFoundException(String message) {
        super(message);
    }
}

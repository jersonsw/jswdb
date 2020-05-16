package io.inouty.jswdb.core.exceptions;

import java.util.function.Supplier;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(String message) {
        super(message);
    }

    public static Supplier<GenreNotFoundException> exceptionSupplier(String message) {
        return () -> new GenreNotFoundException(message);
    }
}

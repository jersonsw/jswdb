package io.inouty.jswdb.core.usecases.contracts;

public interface UseCase<I, O> {

    O execute(I input);

}

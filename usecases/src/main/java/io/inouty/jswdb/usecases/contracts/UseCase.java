package io.inouty.jswdb.usecases.contracts;

public interface UseCase<I, O> {

    O execute(I input);

}

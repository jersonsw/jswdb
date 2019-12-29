package io.inouty.jswdb.core.usecases.contracts;

public interface InputUseCase<I> {
    void execute(I input);
}

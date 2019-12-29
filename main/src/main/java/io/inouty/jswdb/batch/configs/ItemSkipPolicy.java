package io.inouty.jswdb.batch.configs;

import io.inouty.jswdb.core.exceptions.Skippable;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

@Component
public class ItemSkipPolicy implements SkipPolicy {

    public boolean shouldSkip(final Throwable t, final int skipCount) throws SkipLimitExceededException {
        return t instanceof Skippable;
    }
}
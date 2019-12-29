package io.inouty.jswdb.batch.jobs;

import io.inouty.jswdb.batch.configs.ItemSkipPolicy;
import io.inouty.jswdb.batch.listeners.ImdbJobListener;
import io.inouty.jswdb.batch.listeners.MovieWriterListener;
import io.inouty.jswdb.batch.processors.ImdbItemsProcessor;
import io.inouty.jswdb.batch.readers.ImdbItemsReader;
import io.inouty.jswdb.batch.writers.ImdbMovieWriter;
import io.inouty.jswdb.core.domain.MovieDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class JSWDbJob {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private ImdbItemsReader imdbItemsReader;
    private ImdbMovieWriter imdbMovieWriter;
    private ImdbItemsProcessor imdbItemsProcessor;
    private ItemSkipPolicy itemSkipPolicy;
    private MovieWriterListener movieWriterListener;
    private TaskExecutor taskExecutor;

    public JSWDbJob(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ImdbItemsReader imdbItemsReader,
            ImdbMovieWriter imdbMovieWriter,
            ImdbItemsProcessor imdbItemsProcessor,
            MovieWriterListener movieWriterListener,
            ItemSkipPolicy itemSkipPolicy,
            @Qualifier("asyncTaskExecutor") TaskExecutor taskExecutor
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.imdbItemsReader = imdbItemsReader;
        this.imdbMovieWriter = imdbMovieWriter;
        this.imdbItemsProcessor = imdbItemsProcessor;
        this.itemSkipPolicy = itemSkipPolicy;
        this.movieWriterListener = movieWriterListener;
        this.taskExecutor = taskExecutor;
    }

    @Bean
    public Job jswDbProcessorJob(ImdbJobListener imdbJobListener) {
        return jobBuilderFactory.get("jswDbProcessorJob")
                .incrementer(new RunIdIncrementer())
                .listener(imdbJobListener)
                .start(step1())
                .build();
    }

    @Bean()
    public Step step1() {
        return stepBuilderFactory.get("jswdb-step-1")
                .<String, MovieDto>chunk(1)
                .reader(imdbItemsReader)
                .processor(imdbItemsProcessor)
                .faultTolerant()
                .skip(RuntimeException.class)
                .skipPolicy(itemSkipPolicy)
                .writer(imdbMovieWriter)
                .listener(movieWriterListener)
                .allowStartIfComplete(true)
                .taskExecutor(taskExecutor)
                .build();
    }

}

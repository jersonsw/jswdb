package io.inouty.jswdb.main.batch.jobs;

import io.inouty.jswdb.domain.movie.MovieDto;
import io.inouty.jswdb.main.batch.configs.ItemSkipPolicy;
import io.inouty.jswdb.main.batch.listeners.ImdbJobListener;
import io.inouty.jswdb.main.batch.listeners.MovieWriterListener;
import io.inouty.jswdb.main.batch.processors.ImdbItemsProcessor;
import io.inouty.jswdb.main.batch.readers.ImdbItemsReader;
import io.inouty.jswdb.main.batch.writers.ImdbMovieWriter;
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
public class JswdbMoviesJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ImdbItemsReader imdbItemsReader;
    private final ImdbMovieWriter imdbMovieWriter;
    private final ImdbItemsProcessor imdbItemsProcessor;
    private final ItemSkipPolicy itemSkipPolicy;
    private final MovieWriterListener movieWriterListener;
    private final TaskExecutor taskExecutor;

    public JswdbMoviesJob(
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

    @Bean("moviesJob")
    public Job moviesJob(ImdbJobListener imdbJobListener) {
        return jobBuilderFactory.get("moviesJob")
                .incrementer(new RunIdIncrementer())
                .listener(imdbJobListener)
                .start(step1())
                .build();
    }

    @Bean()
    public Step step1() {
        return stepBuilderFactory.get("moviesJobStep1")
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

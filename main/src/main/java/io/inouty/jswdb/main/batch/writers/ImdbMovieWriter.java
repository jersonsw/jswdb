package io.inouty.jswdb.main.batch.writers;

import io.inouty.jswdb.core.domain.movie.Movie;
import io.inouty.jswdb.core.ports.input.CreateMoviesPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component()
public class ImdbMovieWriter implements ItemWriter<Movie> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbMovieWriter.class);
    private static final Map<String, Object> HEADERS = new HashMap<String, Object>() {{
        put("Content-Type", "application/json");
    }};
    private final CreateMoviesPort createMoviesPort;
    private ExecutionContext context;
    private int moviesWritten = 0;
    private SimpMessageSendingOperations messagingTemplate;


    public ImdbMovieWriter(CreateMoviesPort CreateMoviesPort, SimpMessageSendingOperations messagingTemplate) {
        this.createMoviesPort = CreateMoviesPort;
        this.messagingTemplate = messagingTemplate;
    }

    @BeforeStep
    private void beforeStep(StepExecution stepExecution) {
        this.context = stepExecution.getJobExecution().getExecutionContext();
    }

    @Override
    public synchronized void write(List<? extends Movie> movies) {
        try {
            this.createMoviesPort.saveAll(new HashSet<>(movies));
            moviesWritten += movies.size();
            Integer recordsCount = context.getInt("recordsCount");
            Long startTime = context.getLong("startTime");
            this.messagingTemplate.convertAndSend("/topic/progress", new HashMap<String, Object>() {{
                put("recordsCount", recordsCount);
                put("recordsWritten", moviesWritten);
                put("startTime", startTime);
                put("endTime", System.currentTimeMillis());
            }}, HEADERS);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}

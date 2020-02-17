package io.inouty.jswdb.main.batch.listeners;


import io.inouty.jswdb.core.domain.movie.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieWriterListener implements ItemWriteListener<Movie> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieWriterListener.class);

    private static final Map<String, Object> HEADERS = new HashMap<String, Object>() {{
        put("Content-Type", "application/json");
    }};

    private SimpMessageSendingOperations messagingTemplate;

    public MovieWriterListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void beforeWrite(List<? extends Movie> items) {
    }

    @Override
    public void afterWrite(List<? extends Movie> items) {
        this.messagingTemplate.convertAndSend("/topic/savedMovies", items, HEADERS);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Movie> items) {
        LOGGER.error(exception.getMessage(), items);
        this.messagingTemplate.convertAndSend("/topic/failedMovies", items, HEADERS);
    }
}

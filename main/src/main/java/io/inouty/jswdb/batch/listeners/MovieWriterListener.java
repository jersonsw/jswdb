package io.inouty.jswdb.batch.listeners;


import io.inouty.jswdb.core.domain.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieWriterListener implements ItemWriteListener<MovieDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieWriterListener.class);

    private static final Map<String, Object> HEADERS = new HashMap<String, Object>() {{
        put("Content-Type", "application/json");
    }};

    private SimpMessageSendingOperations messagingTemplate;

    public MovieWriterListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void beforeWrite(List<? extends MovieDto> items) {
    }

    @Override
    public void afterWrite(List<? extends MovieDto> items) {
        this.messagingTemplate.convertAndSend("/topic/savedMovies", items, HEADERS);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends MovieDto> items) {
        LOGGER.error(exception.getMessage(), items);
        this.messagingTemplate.convertAndSend("/topic/failedMovies", items, HEADERS);
    }
}

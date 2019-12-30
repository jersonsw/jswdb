package io.inouty.jswdb.web.ws.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class EventsListener {

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) throws JsonProcessingException {
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) throws JsonProcessingException {
    }
}
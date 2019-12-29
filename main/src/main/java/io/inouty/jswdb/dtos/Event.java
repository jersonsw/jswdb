package io.inouty.jswdb.dtos;

import io.inouty.jswdb.enums.EventStatus;

public class Event {

    private EventStatus status;
    private String message;

    public Event() {
    }

    public Event(EventStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public EventStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

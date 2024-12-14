package com.record_application_events;

import org.springframework.context.ApplicationEvent;

public class UserPublishEvent extends ApplicationEvent {
    private final String username;
    private final String content;
    private final Exception exception;

    public UserPublishEvent(Object source, String username, String content, Exception exception) {
        super(source);
        this.username = username;
        this.content = content;
        this.exception = exception;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Exception getException() {
        return exception;
    }
}


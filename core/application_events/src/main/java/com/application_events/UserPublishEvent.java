package com.application_events;

import org.springframework.context.ApplicationEvent;

public class UserPublishEvent extends ApplicationEvent {
    private final String username;
    private final String content;

    public UserPublishEvent(Object source, String username, String content) {
        super(source);
        this.username = username;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }
}


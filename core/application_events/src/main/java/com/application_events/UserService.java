package com.application_events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final ApplicationEventPublisher eventPublisher;

    public UserService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishContent(String username, String content) {
        // Simulate publishing logic
        System.out.println(username + " has published: " + content);

        // Publish the event
        UserPublishEvent event = new UserPublishEvent(this, username, content);
        eventPublisher.publishEvent(event);
    }
}

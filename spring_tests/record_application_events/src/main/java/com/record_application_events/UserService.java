package com.record_application_events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final ApplicationEventPublisher eventPublisher;

    public UserService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishContent(String username, String content) {
        System.out.println(username + " is attempting to publish: " + content);

        Exception exception = null;
        try {
            if (content.contains("error")) {
                throw new CustomException("Publishing content failed: Content contains error word.");
            }
        } catch (Exception ex) {
            exception = ex;
        }

        // Publish the event, including the exception if it occurred
        UserPublishEvent event = new UserPublishEvent(this, username, content, exception);
        eventPublisher.publishEvent(event);
    }

    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }
}


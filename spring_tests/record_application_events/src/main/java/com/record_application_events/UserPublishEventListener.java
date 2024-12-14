package com.record_application_events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserPublishEventListener {

    @EventListener(condition = "#event.exception != null and #event.exception instanceof T(com.example.demo.UserService.CustomException)")
    public void handleUserPublishEventWithException(UserPublishEvent event) {
        System.err.println("Special handling for CustomException during event: User " +
                event.getUsername() + " attempted to publish \"" + event.getContent() + "\". Exception: " +
                event.getException().getMessage());
    }

    @EventListener(condition = "#event.exception == null")
    public void handleUserPublishEventWithoutException(UserPublishEvent event) {
        System.out.println("User " + event.getUsername() + " successfully published content: " +
                event.getContent());
    }
}

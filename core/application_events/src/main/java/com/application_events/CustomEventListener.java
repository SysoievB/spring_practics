package com.application_events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener {

    @EventListener
    public void handleUserPublishEvent(UserPublishEvent event) {
        System.out.printf("Event received: User %s, published content: %s \n",
                event.getUsername(),
                event.getContent());
    }
}

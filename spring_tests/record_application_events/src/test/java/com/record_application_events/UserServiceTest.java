package com.record_application_events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * <h3>@RecordApplicationEvents</h3>
 * is an annotation that can be applied to a test class to
 * instruct the Spring TestContext Framework to record all application events that
 * are published in the ApplicationContext during the execution of a single test.
 *
 * The recorded events can be accessed via the ApplicationEvents API within tests.
 * */
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RecordApplicationEvents//not works without this annotation
class UserServiceTest {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationEvents applicationEvents;

    @Autowired
    private UserService userService;

    @Test
    void testEventPublishing() {
        //Given
        UserPublishEvent event = new UserPublishEvent(this, "test user", "test content", null);

        //When & Then
        assertThatCode(() -> applicationEventPublisher.publishEvent(event))
                .doesNotThrowAnyException();
        assertThat(applicationEvents.stream(UserPublishEvent.class))
                .hasSize(1)
                .allMatch(e -> e.getUsername().equals("test user") && e.getContent().equals("test content"));
    }

    @Test
    void testUserServicePublishingEvent() {
        //When & Then
        assertThatCode(() -> userService.publishContent("test user", "test content"))
                .doesNotThrowAnyException();
        assertThat(applicationEvents.stream(UserPublishEvent.class))
                .hasSize(1)
                .allMatch(e -> e.getUsername().equals("test user") && e.getContent().equals("test content"));
    }

    @Test
    void testUserServiceHandlesException() {
        //When
        assertThatCode(() -> userService.publishContent("test user", "error content"))
                .doesNotThrowAnyException();


        //Then
        assertThat(applicationEvents.stream(UserPublishEvent.class))
                .hasSize(1)
                .allMatch(e -> e.getUsername().equals("test user") &&
                        e.getContent().equals("error content") &&
                        e.getException() instanceof UserService.CustomException);
    }
}

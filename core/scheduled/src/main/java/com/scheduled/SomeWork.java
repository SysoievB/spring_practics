package com.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SomeWork {
    private final ParamCapture paramCapture;

    void get() {
        int size = 10_000_000;
        for (int i = 0; i <= size; i++) {
            paramCapture.capture(size, i);
            if(i == size) {
                log.info("THE END");
                System.exit(130);
            }
        }
    }
}

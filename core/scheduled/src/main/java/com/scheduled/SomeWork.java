package com.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@Component
@AllArgsConstructor
public class SomeWork {
    private final Logger logger;
    private final List<String> myList = new ArrayList<>();

    public void get() {
        int size = 10_000_000;
        for (int i = 0; i < size; i++) {
            myList.add("Item " + i);
            logger.getProcessedPercentage(myList, size);
        }

        log.info("Finished with list size: {}", myList.size());
    }
}

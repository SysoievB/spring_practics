package com.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
@Slf4j
public class Logger {
    private Double currentPercentage = 0.0;
    private static final DecimalFormat rounding = new DecimalFormat("#.##");

    void getProcessedPercentage(List<String> list, int size) {
        currentPercentage =  !list.isEmpty() ? (double)list.size() / size * 100 : 0.0;
    }

    @Scheduled(fixedDelay = 100)
    public void logListSize() {
        log.info("The current percentage is: {}", rounding.format((double) currentPercentage));
    }

    @Scheduled(fixedDelay = 500)
    public void runTask() {
        System.out.println("Running Scheduled Task every 0,5 seconds!");
    }
    /*
    //for int percentage
    private Integer currentPercentage = 0;

    void getProcessedPercentage(List<String> list, int size) {
        currentPercentage = !list.isEmpty() ? (int) ((double) list.size() / size * 100) : 0;
    }*/
}

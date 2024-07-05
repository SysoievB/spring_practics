package com.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class Logger {
  private final ParamCapture paramCapture;

    @Scheduled(fixedDelayString = "${process.logging.interval}")
    public void logListSize() {
        val overallSize = paramCapture.getParam1();
        val processedAmount = paramCapture.getParam2();
        val percentage = processedAmount * 100/overallSize;
        log.info("The percentage is: {}% -> {} - elements, from overall quantity {}",
                percentage,
                processedAmount,
                overallSize);
    }
}

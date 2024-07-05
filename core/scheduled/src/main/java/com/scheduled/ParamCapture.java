package com.scheduled;

import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConditionalOnProperty(name = "process.logging.enabled", havingValue = "true")
public class ParamCapture {
    private int param1;
    private int param2;

    void capture(int p1, int p2) {
        this.param1 = p1;
        this.param2 = p2;
    }
}

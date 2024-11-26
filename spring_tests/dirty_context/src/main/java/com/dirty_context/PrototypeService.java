package com.dirty_context;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope("prototype")
public class PrototypeService {
    private int counter = 0;

    public void incrementCounter() {
        counter++;
    }
}

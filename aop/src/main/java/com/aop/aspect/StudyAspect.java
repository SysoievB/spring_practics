package com.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class StudyAspect {

    @Pointcut("@annotation(UpdateCar)")
    public void withAnnotation() {
    }

    @Before(value = "withAnnotation()")
    public String getAnnotation() throws Throwable {
        return null;

    }
}

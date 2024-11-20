package com.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Aspect
@Slf4j
@Component
public class StudyAspect {

    //matching annotation
    @Pointcut("@annotation(UpdateCar)")
    public void withAnnotation() {
    }

    //matching method
    @Pointcut("execution(public * com.aop.service.CarService.getCarById(Integer))")
    public void withExecutionPoint() {
    }

    //matching type
    @Pointcut("within(com.aop.service.CarService)")
    public void withWithinPoint() {
    }

    @Before(value = "withAnnotation()")
    public void getAnnotation(JoinPoint jp) {
        StackWalker.getInstance()
                .walk(Stream::findFirst)
                .map(StackWalker.StackFrame::getMethodName)
                .ifPresent(methodName -> aspectMethodLog().accept(methodName));
        getLog(jp);
    }

    @After(value = "withExecutionPoint()")
    public void getExecutionPoint(JoinPoint jp) {
        StackWalker.getInstance()
                .walk(Stream::findFirst)
                .map(StackWalker.StackFrame::getMethodName)
                .ifPresent(methodName -> aspectMethodLog().accept(methodName));
        getLog(jp);
    }

    @AfterReturning(value = "withWithinPoint()")
    public void getWithinPoint(JoinPoint jp) {
        StackWalker.getInstance()
                .walk(Stream::findFirst)
                .map(StackWalker.StackFrame::getMethodName)
                .ifPresent(methodName -> aspectMethodLog().accept(methodName));
        getLog(jp);
    }

    private void getLog(JoinPoint jp) {
        val methodName = jp.getSignature().getName();
        log.info("EXECUTING METHOD --------> " + methodName);
    }

    private Consumer<String> aspectMethodLog() {
        return v -> log.info("EXECUTING ASPECT METHOD --------> " + v);
    }
}

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

    /**
     * If BarDaoImpl implements BarDao, the proxy for BarDaoImpl will match this pointcut
     */
    @Pointcut("target(com.aop.aop_classes.BarDao)")
    public void withTargetPointcut() {
    }

    /**
     * If the proxy is of type FooDao, this pointcut will match.
     * Note:
     * <p>
     * If you're using JDK dynamic proxies, it matches interfaces implemented by the proxy.
     * If you're using CGLIB proxies, it matches the class of the proxy.
     */
    @Pointcut("this(com.aop.aop_classes.BarDao)")
    public void withThisPointcut() {
    }

    /**
     * <h6>@Target(ElementType.TYPE)</h6>
     * Ensures the annotation can only be applied to classes, interfaces, or enums.
     */
    @Pointcut("@target(com.aop.aspect.CustomService)")
    public void withAnnotationTargetPointcut() {
    }

    /**
     * Pointcut matching methods with a single argument of type 'Integer'
     */
    @Pointcut("args(java.lang.Integer)")
    public void withArgsPointcut() {
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

    @Before(value = "withTargetPointcut()")
    public void getTargetPoint(JoinPoint jp) {
        StackWalker.getInstance()
                .walk(Stream::findFirst)
                .map(StackWalker.StackFrame::getMethodName)
                .ifPresent(methodName -> aspectMethodLog().accept(methodName));
        getLog(jp);
    }

    @AfterReturning(value = "withThisPointcut()")
    public void getThisPoint(JoinPoint jp) {
        StackWalker.getInstance()
                .walk(Stream::findFirst)
                .map(StackWalker.StackFrame::getMethodName)
                .ifPresent(methodName -> aspectMethodLog().accept(methodName));
        getLog(jp);
    }

    @Before("withArgsPointcut()")
    public void getArgsPoint(JoinPoint jp) {
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

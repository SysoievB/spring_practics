package com.aop.aspect;

import com.aop.model.Vehiclable;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class UpdateCarAspect {

    @Pointcut("@annotation(UpdateCar)")
    public void withAnnotation() {
    }

    @Pointcut("execution(reactor.core.publisher.Mono<java.util.List<com.aop.model.Vehiclable+>> *(..))")
    public void executionOnCollection() {
    }

    @Pointcut("execution(reactor.core.publisher.Mono<com.aop.model.Vehiclable+> *(..))")
    public void executionOnSingle() {
    }


    @Around(value = "executionOnSingle()")
    public Mono<? extends Vehiclable> updateOnSingle(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        val returnValue = (Mono<? extends Vehiclable>) proceedingJoinPoint.proceed();
        return returnValue
                .flatMap(this::update);
    }

    @Around(value = "executionOnCollection()")
    public Mono<? extends Iterable<? extends Vehiclable>> updateOnCollection(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        val returnValue = (Mono<? extends Iterable<? extends Vehiclable>>) proceedingJoinPoint.proceed();
        return returnValue
                .flatMapMany(Flux::fromIterable)
                .flatMap(this::update)
                .collectList();
    }

    private Mono<? extends Vehiclable> update(Vehiclable car) {
        car.setAuto(true);
        return Mono.just(car);
    }
}

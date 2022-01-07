package net.junhabaek.aoporder.common.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(value = 0)
public class MiddlePrecedenceAspect {

    @Pointcut("@annotation(net.junhabaek.aoporder.common.aop.order.MiddlePrecedenceAnnotation)")
    public void annotatedMethods(){}

    @Around("annotatedMethods()")
    public Object testAround(ProceedingJoinPoint joinPoint) throws Throwable{

        log.info("in MiddlePrecedence Aspect's around-before 3");

        Object result = joinPoint.proceed();

        log.info("in MiddlePrecedence Aspect's around-after 7");

        return result;
    }

    @Before("annotatedMethods()")
    public void testBefore(JoinPoint jp) throws Throwable{
        log.info("in MiddlePrecedence Aspect's before 4");
    }

    @AfterReturning("annotatedMethods()")
    public void testAfterReturning(JoinPoint jp) throws Throwable{
        log.info("in MiddlePrecedence Aspect's afterReturning 5");
    }

    @After("annotatedMethods()")
    public void testAfter(JoinPoint jp) throws Throwable{
        log.info("in MiddlePrecedence Aspects' after 6");
    }

    // @AfterThrowing("annotatedMethods()")
}


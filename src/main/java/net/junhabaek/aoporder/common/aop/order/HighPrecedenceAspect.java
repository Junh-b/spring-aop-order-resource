package net.junhabaek.aoporder.common.aop.order;

import lombok.extern.slf4j.Slf4j;
import net.junhabaek.aoporder.common.aop.exception.OrderConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(value = OrderConstants.HIGH_ORDER)
public class HighPrecedenceAspect {

    @Pointcut("@annotation(net.junhabaek.aoporder.common.aop.order.HighPrecedenceAnnotation)")
    public void annotatedMethods(){}

    @Around("annotatedMethods()")
    public Object testAround(ProceedingJoinPoint joinPoint) throws Throwable{

        log.info("in HighPrecedence Aspect's around-before 1");

        Object result = joinPoint.proceed();

        log.info("in HighPrecedence Aspect's around-after 10");

        return result;
    }

    @Before("annotatedMethods()")
    public void testBefore(JoinPoint jp) throws Throwable{
        log.info("in HighPrecedence Aspect's before 2");
    }

    @AfterReturning("annotatedMethods()")
    public void testAfterReturning(JoinPoint jp) throws Throwable{
        log.info("in HighPrecedence Aspect's afterReturning 8");
    }

    @After("annotatedMethods()")
    public void testAfter(JoinPoint jp) throws Throwable{
        log.info("in HighPrecedence Aspects' after 9");
    }

    // @AfterThrowing("annotatedMethods()")
}


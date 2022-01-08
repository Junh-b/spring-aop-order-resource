package net.junhabaek.aoporder.common.aop.exception.low;

import lombok.extern.slf4j.Slf4j;
import net.junhabaek.aoporder.common.aop.exception.OrderConstants;
import net.junhabaek.aoporder.common.aop.exception.TestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(value = OrderConstants.LOW_ORDER)
public class LowPrecedenceAfterExceptionAspect {

    @Pointcut("@annotation(net.junhabaek.aoporder.common.aop.exception.low.LowPrecedenceAfterExceptionAnnotation)")
    public void exceptionOccurringPoint(){}

    @After("exceptionOccurringPoint()")
    public void occurExceptionAfter(JoinPoint jp) throws Throwable{
        if(always()){
            throw new TestException("exception occurred in low precedence after");
        }
    }

    private boolean always(){
        return true;
    }
}

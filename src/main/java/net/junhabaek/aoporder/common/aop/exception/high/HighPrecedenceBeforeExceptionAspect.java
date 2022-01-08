package net.junhabaek.aoporder.common.aop.exception.high;

import lombok.extern.slf4j.Slf4j;
import net.junhabaek.aoporder.common.aop.exception.OrderConstants;
import net.junhabaek.aoporder.common.aop.exception.TestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(value = OrderConstants.HIGH_ORDER)
public class HighPrecedenceBeforeExceptionAspect {
    @Pointcut("@annotation(net.junhabaek.aoporder.common.aop.exception.high.HighPrecedenceBeforeExceptionAnnotation)")
    public void exceptionOccurringPoint(){}

    @Before("exceptionOccurringPoint()")
    public void occurExceptionBefore(JoinPoint jp) throws Throwable{
        if(always()){
            throw new TestException("exception occurred in high precedence before");
        }
    }

    private boolean always(){
        return true;
    }
}

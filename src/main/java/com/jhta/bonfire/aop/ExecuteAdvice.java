package com.jhta.bonfire.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect @Component
public class ExecuteAdvice {
    //return은 무조건 void
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Pointcut("execution(* com.jhta.bonfire.service..*(..))")
    public void timeTarget() {}

    @Around("timeTarget()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("method : "+joinPoint.getSignature().getName());
        for (Object arg : joinPoint.getArgs()) {
            logger.info("param : "+arg);
        }
        long starttime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        // logger.info("res : "+result);
        logger.info(System.currentTimeMillis()-starttime+"ms");
        return result;
    }
}
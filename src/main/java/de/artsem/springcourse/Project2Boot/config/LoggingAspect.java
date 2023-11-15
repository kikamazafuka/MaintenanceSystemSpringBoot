package de.artsem.springcourse.Project2Boot.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

@Around("execution(* de.artsem.springcourse.Project2Boot.services.*.*(..))")
public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("aspect before");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Method " + methodName + " with parameters " + Arrays.asList(args) + " will execute.");
        Object res = joinPoint.proceed();
        logger.info("aspect after " + res);

        return res;
    }
}

package it.tndigit.iot.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Aspect for logging execution of service and repository Spring components.
 *
 * By default, it only runs with the "dev" profile.
 */
@Aspect
@Component
public class LoggingAspect {
    private Map<String, Logger> loggers = new HashMap<>();
    protected Logger loggerFor(String className){
        Logger logger = loggers.get(className);
        if(logger == null){
            synchronized (this){
                logger=  loggers.computeIfAbsent(className, LoggerFactory::getLogger);
            }
        }
        return logger;
    }



    private final Environment env;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

    @Around("@annotation(it.tndigit.iot.logging.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Logger log = loggerFor(joinPoint.getSignature().getDeclaringTypeName());
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info(" Call EndPoint " + joinPoint.getSignature() + "  -- executed in " + executionTime + "ms");
        return proceed;
    }



}

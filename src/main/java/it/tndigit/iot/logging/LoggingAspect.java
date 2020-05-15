//package it.tndigit.iot.logging;
//
//import it.tndigit.iot.domain.logging.LoggingPO;
//import it.tndigit.iot.repository.LoggingRepository;
//import it.tndigit.iot.service.dto.CommonDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.env.Environment;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Aspect for logging execution of service and repository Spring components.
// *
// * By default, it only runs with the "dev" profile.
// */
////@Aspect
//@Component
//@Slf4j
//public class LoggingAspect {
//
//    @Autowired
//    LoggingRepository loggingRepository;
//
//
//    @Autowired
//    ApplicationContext applicationContext;
//
//    private Map<String, Logger> loggers = new HashMap<>();
//
//    protected Logger loggerFor(String className){
//        Logger logger = loggers.get(className);
//        if(logger == null){
//            synchronized (this){
//                logger=  loggers.computeIfAbsent(className, LoggerFactory::getLogger);
//            }
//        }
//        return logger;
//    }
//
//    private final Environment env;
//    public LoggingAspect(Environment env) {
//        this.env = env;
//    }
//
//
//    @Around("@annotation(it.tndigit.iot.logging.LogExecutionTime)")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        Logger log = loggerFor(joinPoint.getSignature().getDeclaringTypeName());
//        long start = System.currentTimeMillis();
//        Object proceed = joinPoint.proceed();
//        long executionTime = System.currentTimeMillis() - start;
//        log.info(" Call EndPoint " + joinPoint.getSignature() + "  -- executed in " + executionTime + "ms");
//
//        LoggingPO loggingPO =applicationContext.getBean(LoggingPO.class);
//        loggingPO.setMethod(joinPoint.getSignature().getDeclaringTypeName() );
//        if (joinPoint.proceed() instanceof ResponseEntity){
//            loggingPO.setReturnCode(((ResponseEntity)joinPoint.proceed()).getStatusCode().toString());
//            if (((ResponseEntity)joinPoint.proceed()).getBody() instanceof CommonDTO){
//                loggingPO.setIdMessage(((CommonDTO)((ResponseEntity)joinPoint.proceed()).getBody()).getIdObj());
//            }
//        }
//        try{
//            loggingRepository.saveAndFlush(loggingPO);
//        }catch (Exception ex){
//            log.error(ex.getMessage());
//        }
//
//        return proceed;
//    }
//
//
//    @After("execution(* it.tndigit.iot.web.rest..*(..)))")
//    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
//    {
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//        //Get intercepted method details
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//
//        final StopWatch stopWatch = new StopWatch();
//
//        //Measure method execution time
//        stopWatch.start();
//        Object result = proceedingJoinPoint.proceed();
//        stopWatch.stop();
//
//        //Log method execution time
//        log.info("Execution time of " + className + "." + methodName + " "
//                + ":: " + stopWatch.getTotalTimeMillis() + " ms");
//
//        return result;
//    }
//
//
//
//}

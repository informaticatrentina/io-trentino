//package it.tndigit.iot.logging;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.BadRequestException;
//import java.util.Arrays;
//
///**
// * Aspect for logging execution of service and repository Spring components.
// *
// * By default, it only runs with the "dev" profile.
// */
//@Aspect
//@Component
//@Slf4j
//public class InterceptResourceAnnotation {
//
//
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//    public void restControllerExecution() {}
//
//
//    //@Before("restControllerExecution()")
//    public void writeLogBefore(JoinPoint joinPoint) throws Throwable {
//
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getResponse();
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        log.info("URL : " + request.getRequestURL().toString());
//        log.info("HTTP_METHOD : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        log.info("RESPONSE : " + response.getStatus());
//
//
//    }
//
//
//    //@After("restControllerExecution()")
//    public void writeLogAfter(JoinPoint joinPoint) throws Throwable {
//
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getResponse();
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        log.info("URL : " + request.getRequestURL().toString());
//        log.info("HTTP_METHOD : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        log.info("RESPONSE : " + response.getStatus());
//
//
//    }
//
//
//    //@AfterThrowing("restControllerExecution()")
//    public void writeErrorLogAfter(JoinPoint joinPoint) throws Throwable {
//
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getResponse();
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("ERRORE");
//        log.info("URL : " + request.getRequestURL().toString());
//        log.info("HTTP_METHOD : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        log.info("RESPONSE : " + response.getStatus());
//
//    }
//
//
//
//    @Around("restControllerExecution()")
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
//}

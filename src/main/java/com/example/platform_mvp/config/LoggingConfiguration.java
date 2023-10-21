package com.example.platform_mvp.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingConfiguration {

    @Pointcut("execution(public * com.example.platform_mvp.controller.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * com.example.platform_mvp.service.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeControllerLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info(" NEW REQUEST:\n IP : {}\n URL : {}\n HTTP_METHOD : {}\n CONTROLLER_METHOD : {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Before("serviceLog()")
    public void doBeforeServiceLog(JoinPoint joinPoint) {
        log.info(" RUN SERVICE: \n SERVICE_METHOD: {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturn(Object returnObject) {
        if(log.isInfoEnabled()){
            log.info(" Return value: {}\n END OF REQUEST! ", returnObject);
        }
    }

    @AfterThrowing(throwing = "exception", pointcut = "controllerLog()")
    public void throwException(JoinPoint joinPoint, Exception exception) {
        log.error(" Request throw an exception. Cause - {}.{}",
                Arrays.toString(joinPoint.getArgs()), exception.getMessage());
    }

}

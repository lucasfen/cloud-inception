package com.lucas.annotation.Log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Lucasfen
 * @Date 2021/06/07
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(com.lucas.annotation.Log.Log)")
    public Object handlerPrintLog(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] param = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for (Object o : param) {
            sb.append(o + "; ");
        }
        log.info("<<{}>>, param: {}", methodName, sb.toString());

        Object object = null;

        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("error", throwable);
        }
        log.info("{} end", methodName);
        return object;
    }
}

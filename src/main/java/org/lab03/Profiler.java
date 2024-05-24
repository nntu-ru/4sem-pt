package org.lab03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.lab03.iface.ILogger;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class Profiler {
    private ILogger logger;

    public Profiler(ILogger logger) {
        this.logger = logger;
    }

    @Around("execution(* org.lab03.Event.*(..))")
    private Object profileEvent(ProceedingJoinPoint jp) throws Throwable {
        LocalTime time = LocalTime.now();

        Object r = jp.proceed();

        logger.debug("profileEvent( "
                + jp.getSignature() + " -> "
                + (LocalTime.now().toNanoOfDay() - time.toNanoOfDay() + " ns")
                + ")");
        return r;
    }
}

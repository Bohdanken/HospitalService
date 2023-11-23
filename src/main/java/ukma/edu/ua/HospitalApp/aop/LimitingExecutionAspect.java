package ukma.edu.ua.HospitalApp.aop;

import com.google.maps.internal.ratelimiter.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LimitingExecutionAspect {

    @Pointcut("execution(* ukma.edu.ua.HospitalApp.api.prescription.PrescriptionController.limit())")
    public void limitMethod() {}

    private final RateLimiter rateLimiter = RateLimiter.create(1);

    @Around("limitMethod()")
    public Object rateLimit(ProceedingJoinPoint joinPoint) {

        if (!rateLimiter.tryAcquire()) {
            return "wait";
        }

        Object value = null;

        try {
            value = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return value;
    }
}

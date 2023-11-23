package ukma.edu.ua.HospitalApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    @Pointcut("execution(* ukma.edu.ua.HospitalApp.api.patient.PatientController.*(..))")
    public void patientControllerMethods() {}

    @AfterThrowing("patientControllerMethods()")
    public void afterThrowingMethodCall(JoinPoint jp) {
        System.out.println(jp.getSignature().getName() + " Error handling");
    }

    @Around("patientControllerMethods()")
    public Object aroundMethodCall(ProceedingJoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object value;

        try {
            value = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            value = methodName + " error";
        }
        return value;
    }
}

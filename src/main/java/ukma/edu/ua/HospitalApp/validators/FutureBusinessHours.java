package ukma.edu.ua.HospitalApp.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = FutureBusinessHourValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureBusinessHours {

    String message() default "The date should be in the future, on working days from 9 to 17 hours";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

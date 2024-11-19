package ukma.edu.ua.AuthService.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Enum<?>> {
	private List<String> acceptedValues;

	@Override
	public void initialize(ValueOfEnum annotation) {
		acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
				.map(Enum::name)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		return acceptedValues.contains(value.toString());
	}
}

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
	Class<? extends Enum<?>> enumClass();

	String message() default "must be any of enum {enumClass}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

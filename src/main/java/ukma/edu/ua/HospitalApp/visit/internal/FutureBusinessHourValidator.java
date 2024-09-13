package ukma.edu.ua.HospitalApp.visit.internal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Timestamp;
import java.util.Calendar;

public class FutureBusinessHourValidator implements ConstraintValidator<FutureBusinessHours, Timestamp> {

    @Override
    public boolean isValid(Timestamp dateOfVisit, ConstraintValidatorContext context) {
        if (dateOfVisit == null) {
            return true;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateOfVisit.getTime());

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        // Перевірка, що дата в майбутньому
        if (dateOfVisit.before(new Timestamp(System.currentTimeMillis()))) {
            return false;
        }

        // Перевірка, що не субота або неділя (Calendar.SUNDAY = 1, Calendar.SATURDAY = 7)
        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
            return false;
        }

        // Перевірка, що час між 9 і 17 годинами
        return hourOfDay >= 9 && hourOfDay < 17;
    }
}

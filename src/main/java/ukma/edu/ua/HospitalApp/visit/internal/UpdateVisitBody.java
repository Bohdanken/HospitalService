package ukma.edu.ua.HospitalApp.visit.internal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UpdateVisitBody {
    @NotNull
    @FutureBusinessHours
    private Timestamp dateOfVisit;
}

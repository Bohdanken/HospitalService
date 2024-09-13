package ukma.edu.ua.HospitalApp.visit.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ukma.edu.ua.HospitalApp.visit.FutureBusinessHours;

import java.sql.Timestamp;

@Data
public class UpdateVisitBody {
    @NotNull
    @FutureBusinessHours
    private Timestamp dateOfVisit;
}

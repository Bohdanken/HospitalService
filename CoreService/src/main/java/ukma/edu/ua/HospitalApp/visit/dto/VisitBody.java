package ukma.edu.ua.HospitalApp.visit.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ukma.edu.ua.HospitalApp.common.validators.FutureBusinessHours;

import java.sql.Timestamp;

@Data
public class VisitBody {
    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    @NotNull
    @FutureBusinessHours
    private Timestamp dateOfVisit;
}

package ukma.edu.ua.VisitService.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ukma.edu.ua.VisitService.validators.FutureBusinessHours;

import java.sql.Timestamp;

@Data
public class VisitBody {
    @NotNull
    private Long doctorId;

    @NotNull
    @FutureBusinessHours
    private Timestamp dateOfVisit;
}

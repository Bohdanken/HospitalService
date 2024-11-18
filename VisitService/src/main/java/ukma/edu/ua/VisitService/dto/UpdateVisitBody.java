package ukma.edu.ua.VisitService.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ukma.edu.ua.VisitService.validators.FutureBusinessHours;

import java.sql.Timestamp;

@Data
public class UpdateVisitBody {
    @NotNull
    @FutureBusinessHours
    private Timestamp dateOfVisit;
}

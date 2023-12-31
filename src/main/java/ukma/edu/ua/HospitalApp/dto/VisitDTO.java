package ukma.edu.ua.HospitalApp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class VisitDTO {

    @NonNull
    private Long id;

    @NonNull
    private Long patientId;

    @NonNull
    private Long doctorId;

    @NonNull
    private Timestamp dateOfVisit;
}

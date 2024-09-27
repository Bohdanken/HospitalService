package ukma.edu.ua.HospitalApp.entities.src.main.java.dto;

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

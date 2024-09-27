package ukma.edu.ua.HospitalApp.visit.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.DoctorDetails;
import ukma.edu.ua.HospitalApp.validators.FutureBusinessHours;

import java.sql.Timestamp;

@Data
public class VisitBody {
    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    private DoctorDetails.DoctorType doctorType;

    @NotNull
    @FutureBusinessHours
    private Timestamp dateOfVisit;
}

package ukma.edu.ua.HospitalApp.visit.internal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorDetails;

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
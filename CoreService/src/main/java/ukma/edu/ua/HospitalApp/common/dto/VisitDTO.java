package ukma.edu.ua.HospitalApp.common.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VisitDTO {
	@NotNull
	private Long id;

	@NotNull
	private Timestamp dateOfVisit;

	@NotNull
	private Long patientId;

	@NotNull
	private Long doctorId;

	@NotNull
	private DoctorDTO doctor;

	@NotNull
	private PatientDTO patient;
}

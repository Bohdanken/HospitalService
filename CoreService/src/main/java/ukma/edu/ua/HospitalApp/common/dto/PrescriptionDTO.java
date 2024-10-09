package ukma.edu.ua.HospitalApp.common.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ukma.edu.ua.HospitalApp.common.entities.Patient;

@Data
@Builder
public class PrescriptionDTO {
	@NotNull
	private Long id;

	@NotNull
	private Date dateOfIssue;

	@NotNull
	private Long patientId;

	@NotNull
	private Patient patient;

	@NotNull
	@NotEmpty
	private DrugDTO[] drugs;
}

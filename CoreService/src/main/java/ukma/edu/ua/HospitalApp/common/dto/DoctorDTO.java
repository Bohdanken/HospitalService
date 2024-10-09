package ukma.edu.ua.HospitalApp.common.dto;

import java.util.Date;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ukma.edu.ua.HospitalApp.common.entities.Doctor.DoctorType;

@Data
public class DoctorDTO {
	@NotNull
	private Long id;

	@NotNull
	private String email;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private Date birthDate;

	@NotNull
	private DoctorType doctorType;
}

package ukma.edu.ua.HospitalApp.common.dto;

import java.util.Date;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientDTO {
	@NotNull
	private Long id;

	@NotNull
	private String email;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String address;

	@NotNull
	private String passportNumber;

	@NotNull
	private Date birthDate;
}

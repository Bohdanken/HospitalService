package ukma.edu.prescription.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePrescriptionBody {
	@NotNull
	private Long patientId;

	@NotNull
	@Length(max = 50)
	private String patientFirstName;

	@NotNull
	@Length(max = 50)
	private String patientLastName;

	@NotNull
	@NotEmpty
	@Size(max = 3)
	private Drug[] drugs;

	@Data
	@Builder
	public static class Drug {
		@NotNull
		@Length(max = 50)
		private String name;

		@NotNull
		private Integer timesPerDay;
	}
}

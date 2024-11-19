package ukma.edu.ua.PrescriptionService.dto;

import java.util.List;

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
	@Size(min = 1)
	private List<Drug> drugs;

	@Data
	@Builder
	public static class Drug {
		@NotNull
		private Long id;

		@NotNull
		private Integer timesPerDay;
	}
}

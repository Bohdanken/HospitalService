package ukma.edu.ua.HospitalApp.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrugDTO {
	@NotNull
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String producer;
}

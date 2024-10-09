package ukma.edu.ua.HospitalApp.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HospitalDTO {
	@NotNull
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String address;
}

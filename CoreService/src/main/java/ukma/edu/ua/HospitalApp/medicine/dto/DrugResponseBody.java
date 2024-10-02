package ukma.edu.ua.HospitalApp.medicine.dto;

import lombok.Data;

@Data
public class DrugResponseBody {
	private Long id;
	private String brandName;
	private String genericName;
	private String producer;
	private String description;
}

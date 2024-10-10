package ukma.edu.prescription.dto;

import java.util.Date;
import lombok.Data;

@Data
public class PrescriptionDTO {
	private Long id;
	private Date dateOfIssue;
	private Long patientId;
	private String prescriptionDetails;
}

package ukma.edu.ua.HospitalApp.prescription.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PrescriptionResponse {
	private Long id;
	private Date dateOfIssue;
	private Long patientId;
	private String prescriptionDetails;
}

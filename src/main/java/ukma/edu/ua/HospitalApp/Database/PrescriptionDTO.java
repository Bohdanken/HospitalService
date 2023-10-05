package ukma.edu.ua.HospitalApp.Database;

import lombok.Data;

import java.util.Date;

@Data
public class PrescriptionDTO {
	private long id;
	Date dateOfIssue;
}
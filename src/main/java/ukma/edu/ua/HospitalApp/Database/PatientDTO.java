package ukma.edu.ua.HospitalApp.Database;

import lombok.Data;

@Data
public class PatientDTO {
	private long id;
	private String first_name;
	private String last_name;
	String email;
	String address;
	String passportNumber;
	int yearOfBirth;
}
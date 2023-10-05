package ukma.edu.ua.HospitalApp.Database;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DoctorDTO {
	private long id;
	private String first_name;
	private String last_name;
	String email;
	int age;
}
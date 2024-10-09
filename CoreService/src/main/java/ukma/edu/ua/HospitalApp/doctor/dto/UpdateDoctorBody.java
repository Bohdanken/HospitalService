package ukma.edu.ua.HospitalApp.doctor.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Data;
import ukma.edu.ua.HospitalApp.common.entities.Doctor.DoctorType;
import ukma.edu.ua.HospitalApp.common.validators.ValueOfEnum;

@Data
public class UpdateDoctorBody {
	@Email
	private String email;

	@Length(min = 1, max = 30)
	private String firstName;

	@Length(min = 1, max = 30)
	private String lastName;

	@Past
	private Date birthDate;

	@ValueOfEnum(enumClass = DoctorType.class)
	private DoctorType doctorType;
}

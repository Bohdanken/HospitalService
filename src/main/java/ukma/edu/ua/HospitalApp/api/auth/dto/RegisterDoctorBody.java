package ukma.edu.ua.HospitalApp.api.auth.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ukma.edu.ua.HospitalApp.config.validation.enums.ValueOfEnum;
import ukma.edu.ua.HospitalApp.models.Doctor;
import ukma.edu.ua.HospitalApp.models.Doctor.DoctorType;

@Data
public class RegisterDoctorBody {
  @Length(min = 1, max = 30)
  private String firstName;

  @Length(min = 1, max = 30)
  private String lastName;
  
  @Past
  private Date birthDate;

  @ValueOfEnum(enumClass = Doctor.DoctorType.class)
  private DoctorType doctorType;
 
  @Min(1)
  private Long hospitalId;

  @NotEmpty()
  private String password;
}

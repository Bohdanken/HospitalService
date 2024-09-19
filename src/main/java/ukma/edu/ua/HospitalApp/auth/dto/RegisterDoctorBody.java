package ukma.edu.ua.HospitalApp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import ukma.edu.ua.HospitalApp.entities.DoctorDetails;
import ukma.edu.ua.HospitalApp.entities.DoctorDetails.DoctorType;
import ukma.edu.ua.HospitalApp.validators.ValueOfEnum;

@Data
public class RegisterDoctorBody {
  @Email
  private String email;

  @NotNull
  private String password;

  @Length(min = 1, max = 30)
  private String firstName;

  @Length(min = 1, max = 30)
  private String lastName;

  @Past
  private Date birthDate;

  @ValueOfEnum(enumClass = DoctorDetails.DoctorType.class)
  private DoctorType doctorType;

  @Min(1)
  private Long hospitalId;
}

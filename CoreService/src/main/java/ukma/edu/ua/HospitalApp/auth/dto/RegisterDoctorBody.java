package ukma.edu.ua.HospitalApp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import ukma.edu.ua.HospitalApp.common.entities.Doctor;
import ukma.edu.ua.HospitalApp.common.entities.Doctor.DoctorType;
import ukma.edu.ua.HospitalApp.common.validators.ValueOfEnum;

import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class RegisterDoctorBody {
  @Email
  @NotNull
  private String email;

  @NotNull
  private String password;

  @Length(min = 1, max = 30)
  @NotNull
  private String firstName;

  @Length(min = 1, max = 30)
  @NotNull
  private String lastName;

  @Past
  @NotNull
  private Date birthDate;

  @ValueOfEnum(enumClass = Doctor.DoctorType.class)
  @NotNull
  private DoctorType doctorType;

  @Min(1)
  @NotNull
  private Long hospitalId;
}

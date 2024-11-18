package ukma.edu.ua.AuthService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import ukma.edu.ua.AuthService.validators.ValueOfEnum;
import ukma.edu.ua.AuthService.entities.Doctor.DoctorType;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class RegisterDoctorBody {
  @Email
  @NotNull
  @Schema(example = "doc11@gmail.com", description = "User email")
  private String email;

  @NotNull
  @Schema(example = "", description = "User password")
  private String password;

  @Length(min = 1, max = 30)
  @NotNull
  @Schema(example = "Oleg", description = "Doctor's name")
  private String firstName;

  @Length(min = 1, max = 30)
  @NotNull
  @Schema(example = "Ivanov", description = "Doctor's last name")
  private String lastName;

  @Past
  @NotNull
  @Schema(description = "Doctor's date of birth")
  private Date birthDate;

  @ValueOfEnum(enumClass = DoctorType.class)
  @NotNull
  @Schema(description = "Doctor's type")
  private DoctorType doctorType;
}

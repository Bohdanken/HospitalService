package ukma.edu.ua.HospitalApp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class RegisterPatientBody {
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

  @Length(min = 1, max = 100)
  @NotNull
  private String address;

  @Pattern(regexp = "^[A-Z]{2}[0-9]{6}$")
  @NotNull
  private String passportNumber;

  @Past
  @NotNull
  private Date birthDate;
}

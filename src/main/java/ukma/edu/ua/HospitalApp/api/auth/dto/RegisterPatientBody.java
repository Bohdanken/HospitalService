package ukma.edu.ua.HospitalApp.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterPatientBody {
  @Email
  private String email;

  @Length(min = 1, max = 30)
  private String firstName;

  @Length(min = 1, max = 30)
  private String lastName;

  @Length(min = 1, max = 100)
  private String address;

  @Pattern(regexp = "^[A-Z]{2}[0-9]{6}$")
  private String passportNumber;

  @Past
  private Date birthDate;

  @NotEmpty()
  private String password;
}

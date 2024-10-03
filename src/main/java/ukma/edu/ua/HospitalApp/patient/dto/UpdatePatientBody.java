package ukma.edu.ua.HospitalApp.patient.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UpdatePatientBody {
  @NotNull
  @Length(max = 20)
  private String firstName;

  @NotNull
  @Length(max = 30)
  private String lastName;

  @NotNull
  @Length(max = 100)
  private String address;

  @NotNull
  @Pattern(regexp = "^[A-Z]{2}\\d{6}$")
  private String passportNumber;

  @NotNull
  @Past
  private Date birthDate;
}

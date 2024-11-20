package ukma.edu.ua.AuthService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPatientBody {
  @Email
  @NotNull
  @Schema(example = "patient101@gmail.com", description = "User email")
  private String email;

  @NotNull
  @Schema(example = "", description = "User password")
  private String password;

  @Length(min = 1, max = 30)
  @NotNull
  @Schema(example = "Ivan", description = "Patient's first name")
  private String firstName;

  @Length(min = 1, max = 30)
  @NotNull
  @Schema(example = "Shenchuk", description = "Patient's last name")
  private String lastName;

  @Length(min = 1, max = 100)
  @NotNull
  @Schema(example = "random street 32", description = "Patient's address")
  private String address;

  @Pattern(regexp = "^[A-Z]{2}[0-9]{6}$")
  @NotNull
  @Schema(example = "VV156152", description = "Patient's passport number")
  private String passportNumber;

  @Past
  @NotNull
  @Schema(description = "Patient's date of birth")
  private Date birthDate;
}

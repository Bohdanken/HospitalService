package ukma.edu.ua.HospitalApp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginBody {
  @NotNull
  @Email()
  String email;

  @NotNull
  String password;
}

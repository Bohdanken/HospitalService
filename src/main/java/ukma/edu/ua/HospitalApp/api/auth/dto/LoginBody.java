package ukma.edu.ua.HospitalApp.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginBody {
  @NotNull()
  @Email()
  String email;

  @NotNull
  String password;
}

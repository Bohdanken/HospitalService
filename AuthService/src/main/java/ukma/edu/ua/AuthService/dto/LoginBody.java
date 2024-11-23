package ukma.edu.ua.AuthService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginBody {
  @NotNull
  @Email()
  @Schema(description = "User email", type = "string", example = "patient1@gmail.com")
  String email;

  @NotNull
  @Schema(description = "User password", type = "string", example = "")
  String password;
}

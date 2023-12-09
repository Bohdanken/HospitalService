package ukma.edu.ua.HospitalApp.api.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;
import ukma.edu.ua.HospitalApp.services.AuthService;

@RestController
@RequestMapping("${app.prefix}/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication routes for users")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public JWTService.TokenResponse login(@RequestBody LoginBody body) {
    return authService.login(body);
  }
}

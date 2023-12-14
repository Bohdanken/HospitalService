package ukma.edu.ua.HospitalApp.api.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;
import ukma.edu.ua.HospitalApp.services.AuthService;

@RestController
@RequestMapping("${app.prefix}")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication routes for users")
public class AuthController {

  @Value("${app.prefix:/api/auth}")
  public String appPrefixInstance;
  private final AuthService authService;
  public static String APP_PREFIX;

  @PostConstruct
  private void init() {
    APP_PREFIX = appPrefixInstance;
  }
  
  @PostMapping("/login")
  public JWTService.TokenResponse login(@ModelAttribute("mLoginBody") LoginBody body) {
    return authService.login(body);
  }
}

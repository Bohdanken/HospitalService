package ukma.edu.ua.HospitalApp.api.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.api.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.api.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.config.Endpoints;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;
import ukma.edu.ua.HospitalApp.services.AuthService;

@RestController
@RequestMapping("${app.prefix}" + Endpoints.AUTH)
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication routes for users")
public class AuthController {
  private final AuthService authService;

  public static final String LOGIN_PATH = "/login";

  public static final String REGISTER_DOCTOR_PATH = "/register/doctor";

  public static final String REGISTER_PATIENT_PATH = "/register/patient";

  @PostMapping(LOGIN_PATH)
  public JWTService.TokenResponse login(@Valid @RequestBody LoginBody body) {
    return authService.login(body);
  }

  @PostMapping(REGISTER_DOCTOR_PATH)
  public JWTService.TokenResponse registerDoctor(@Valid @RequestBody RegisterDoctorBody body) {
    return authService.registerDoctor(body);
  }

  @PostMapping(REGISTER_PATIENT_PATH)
  public JWTService.TokenResponse registerPatient(@Valid @RequestBody RegisterPatientBody body) {
    return authService.registerPatient(body);
  }
}

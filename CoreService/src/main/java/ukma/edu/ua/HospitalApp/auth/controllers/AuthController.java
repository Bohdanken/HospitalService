package ukma.edu.ua.HospitalApp.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ukma.edu.ua.HospitalApp.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.auth.services.AuthService;
import ukma.edu.ua.HospitalApp.common.Endpoints;
import ukma.edu.ua.HospitalApp.common.security.JWTService;

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
  @Operation(summary = "Login as doctor or patient")
  public JWTService.TokenResponse login(@Valid @RequestBody LoginBody body) {
    return authService.login(body);
  }

  @PostMapping(REGISTER_DOCTOR_PATH)
  @Operation(summary = "Register as a doctor")
  public JWTService.TokenResponse registerDoctor(@Valid @RequestBody RegisterDoctorBody body) {
    return authService.registerDoctor(body);
  }

  @PostMapping(REGISTER_PATIENT_PATH)
  @Operation(summary = "Register as a patient")
  public JWTService.TokenResponse registerPatient(@Valid @RequestBody RegisterPatientBody body) {
    return authService.registerPatient(body);
  }
}

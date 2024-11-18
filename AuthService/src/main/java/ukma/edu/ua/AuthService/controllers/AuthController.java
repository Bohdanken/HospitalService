package ukma.edu.ua.AuthService.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ukma.edu.ua.AuthService.dto.LoginBody;
import ukma.edu.ua.AuthService.dto.RegisterDoctorBody;
import ukma.edu.ua.AuthService.dto.RegisterPatientBody;
import ukma.edu.ua.AuthService.services.AuthService;
import ukma.edu.ua.AuthService.services.JWTService.TokenResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication routes for users")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  @Operation(summary = "Login as doctor or patient")
  public TokenResponse login(@Valid @RequestBody LoginBody body) {
    return authService.login(body);
  }

  @PostMapping("/register/doctor")
  @Operation(summary = "Register as a doctor")
  public TokenResponse registerDoctor(@Valid @RequestBody RegisterDoctorBody body) {
    return authService.registerDoctor(body);
  }

  @PostMapping("/register/patient")
  @Operation(summary = "Register as a patient")
  public TokenResponse registerPatient(@Valid @RequestBody RegisterPatientBody body) {
    return authService.registerPatient(body);
  }
}

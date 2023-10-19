package ukma.edu.ua.HospitalApp.api.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  private final Logger logger = LogManager.getLogger(AuthController.class);

  private final Marker loggerMarker = MarkerManager.getMarker("REQUEST_STATUS");

  @PostMapping("/login/patient")
  public PatientDTO loginPatient(@Valid @RequestBody LoginBody body) {
    logger.info(loggerMarker, "Patient has logged in");
    return authService.loginPatient(body);
  }

  @PostMapping("/login/doctor")
  public DoctorDTO loginDoctor(@Valid @RequestBody LoginBody body) {
    logger.info(loggerMarker, "Doctor has logged in");
    ThreadContext.clearAll();
    return authService.loginDoctor(body);
  }
}

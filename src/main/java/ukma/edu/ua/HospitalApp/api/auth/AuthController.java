package ukma.edu.ua.HospitalApp.api.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  private final Logger logger = LogManager.getLogger(AuthController.class);

  private final Marker loggerMarker = MarkerManager.getMarker("REQUEST_STATUS");

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login/patient")
  public AuthService.TokenResponse loginPatient() {
    logger.warn(loggerMarker, "Patient has logged in");
    return authService.login();
  }

  @PostMapping("/login/doctor")
  public AuthService.TokenResponse loginDoctor() {
    logger.warn(loggerMarker, "Doctor has logged in");
    return authService.login();
  }

  @PostMapping("/login/admin")
  public AuthService.TokenResponse loginAdmin() {
    logger.warn(loggerMarker, "Admin has logged in");
    return authService.login();
  }
}

package ukma.edu.ua.HospitalApp.api.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectBodyException;
import ukma.edu.ua.HospitalApp.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication routes for patients, doctors and admins")
public class AuthController {
  private final AuthService authService;

  private final Logger logger = LogManager.getLogger(AuthController.class);

  private final Marker loggerMarker = MarkerManager.getMarker("REQUEST_STATUS");

  @Operation(description = "Patient login")
  @PostMapping("/login/patient")
  public PatientDTO loginPatient(@Valid @RequestBody LoginBody body) {
    logger.info(loggerMarker, "Patient has logged in");
    return authService.loginPatient(body);
  }

  @Operation(description = "Doctor login")
  @PostMapping("/login/doctor")
  public DoctorDTO loginDoctor(@Valid @RequestBody LoginBody body) {
    logger.info(loggerMarker, "Doctor has logged in");
    ThreadContext.clearAll();
    return authService.loginDoctor(body);
  }

  @ExceptionHandler
  public void handleException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(err -> "Поле '" + err.getField() + "' " + err.getDefaultMessage())
        .toList();

    throw new IncorrectBodyException("Incorrect login information", ex, errors);
  }
}

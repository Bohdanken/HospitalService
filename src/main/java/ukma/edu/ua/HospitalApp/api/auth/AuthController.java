package ukma.edu.ua.HospitalApp.api.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectBodyException;
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

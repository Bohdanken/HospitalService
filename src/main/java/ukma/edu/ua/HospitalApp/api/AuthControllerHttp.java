package ukma.edu.ua.HospitalApp.api;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ukma.edu.ua.HospitalApp.api.auth.AuthController;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.services.AuthService;

@Controller
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication routes for patients, doctors and admins")
public class AuthControllerHttp {
  private final AuthService authService;

  public final static Logger logger = LogManager.getLogger(AuthControllerHttp.class);
  private final Marker loggerMarker = MarkerManager.getMarker("REQUEST_STATUS");


  @GetMapping("/login")
  public String loginPage(Model model, RedirectAttributes redirectAttributes) {
    //TODO finish this
    if (AuthService.isAuthenticated()) {
      redirectAttributes.addFlashAttribute("infoMessage", "You are already logged in.");
      return "Need some redirection";
    }
    if (!model.containsAttribute("mLoginBody"))
      model.addAttribute("mLoginBody", new LoginBody());
    model.addAttribute("loginUrl", AuthController.APP_PREFIX +"/login");
    return "login/loginPage";
  }

  @ExceptionHandler
  public ResponseEntity<String> handleException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(err -> "Поле '" + err.getField() + "' " + err.getDefaultMessage())
        .toList();
    String errorMessage = "Incorrect login information: " + String.join(", ", errors);
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorMessage);
  }
}
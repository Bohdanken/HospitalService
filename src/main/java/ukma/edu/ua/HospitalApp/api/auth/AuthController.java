package ukma.edu.ua.HospitalApp.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/patient")
    public AuthService.TokenResponse loginPatient() {
        return authService.login();
    }

    @PostMapping("/login/doctor")
    public AuthService.TokenResponse loginDoctor() {
        return authService.login();
    }

    @PostMapping("/login/admin")
    public AuthService.TokenResponse loginAdmin() {
        return authService.login();
    }
}

package ukma.edu.ua.HospitalApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final AuthenticationConfiguration auth;

  private final JWTService jwtService;

  public JWTService.TokenResponse login(LoginBody data) {
    authenticate(data.getEmail(), data.getPassword());
    return jwtService.generateToken(data.getEmail());
  }

  private void authenticate(String email, String password) {
    try {
      auth
          .getAuthenticationManager()
          .authenticate(
              new UsernamePasswordAuthenticationToken(email, password));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
  }
}
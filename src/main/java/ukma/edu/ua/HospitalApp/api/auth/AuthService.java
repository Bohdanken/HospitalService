package ukma.edu.ua.HospitalApp.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.shared.JWTService;

@Service
public class AuthService {
  public JWTService jwtService;

  @Autowired
  public AuthService(JWTService jwtService) {
    this.jwtService = jwtService;
  }

  public TokenResponse login() {
    return new TokenResponse(
        jwtService.generateAccessToken(),
        jwtService.generateRefreshToken()
    );
  }

  public record TokenResponse(String accessToken, String refreshToken) {
  }
}

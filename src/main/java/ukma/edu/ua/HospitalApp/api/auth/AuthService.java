package ukma.edu.ua.HospitalApp.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.shared.JWTService;

import java.util.Map;

@Service
public class AuthService {
  public JWTService jwtService;
String databaseURL="";
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

    @Autowired
    public void setDatabaseURL( Map<String, String> envVars) {
         databaseURL = envVars.get("DATABASE_URL");
    }


  public record TokenResponse(String accessToken, String refreshToken) {
  }
}

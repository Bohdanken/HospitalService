package ukma.edu.ua.HospitalApp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTService {
  @Value("${app.access-token-lifetime}")
  private int accessTokenLifetime;

  @Value("${app.refresh-token-lifetime}")
  private int refreshTokenLifetime;

  public String generateAccessToken() {
    System.out.println(accessTokenLifetime);
    return null;
  }

  public String generateRefreshToken() {
    System.out.println(refreshTokenLifetime);
    return null;
  }
}

package ukma.edu.ua.VisitService.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import ukma.edu.ua.VisitService.security.User.UserRole;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTService {
  @Value("${app.jwt.access-token-secret}")
  private String accessTokenSecret;

  @Value("${app.jwt.refresh-token-secret}")
  private String refreshTokenSecret;

  @Value("${app.jwt.access-token-lifetime}")
  private long accessTokenLifetime;

  @Value("${app.jwt.refresh-token-lifetime}")
  private long refreshTokenLifetime;

  private SecretKey secret = null;

  private SecretKey getSecret() {
    if (secret == null) {
      secret = Keys.hmacShaKeyFor(Base64.getDecoder().decode(accessTokenSecret));
    }
    return secret;
  }

  public User getUserDataFromToken(String token) {
    try {
      var payload = Jwts
          .parser()
          .verifyWith(getSecret())
          .build()
          .parseSignedClaims(token)
          .getPayload();

      return User.builder()
          .id(((Number) payload.get("user_id")).longValue())
          .email((String) payload.get("email"))
          .role(UserRole.valueOf((String) payload.get("role")))
          .build();
    } catch (Exception e) {
      return null;
    }
  }
}

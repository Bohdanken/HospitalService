package ukma.edu.ua.HospitalApp.common.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
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

  public TokenResponse generateToken(String email) {
    Instant now = Instant.now();
    String accessToken = Jwts
        .builder()
        .subject(email)
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plus(accessTokenLifetime, ChronoUnit.SECONDS)))
        .signWith(getSecret())
        .compact();
    return new TokenResponse(accessToken);
  }

  public String getUserEmailFromToken(String token) {
    try {
      return Jwts
          .parser()
          .verifyWith(getSecret())
          .build()
          .parseSignedClaims(token)
          .getPayload()
          .getSubject();
    } catch (Exception e) {
      return null;
    }
  }

  @Builder
  public static record TokenResponse(String accessToken) {
  }
}

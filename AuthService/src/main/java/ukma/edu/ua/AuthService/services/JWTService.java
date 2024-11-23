package ukma.edu.ua.AuthService.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import ukma.edu.ua.AuthService.entities.User;
import ukma.edu.ua.AuthService.security.AppUser;

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

  public TokenResponse generateToken(AppUser user) {
    Instant now = Instant.now();
    String accessToken = Jwts
        .builder()
        .issuedAt(Date.from(now))
        .issuer("AuthService")
        .claim("email", user.getUsername())
        .claim("role", user.getRole())
        .claim("user_id", user.getUserId())
        .expiration(Date.from(now.plus(accessTokenLifetime, ChronoUnit.SECONDS)))
        .signWith(getSecret())
        .compact();
    return new TokenResponse(accessToken);
  }

  public TokenResponse generateToken(User user) {
    Instant now = Instant.now();
    String accessToken = Jwts
        .builder()
        .issuedAt(Date.from(now))
        .issuer("AuthService")
        .claim("email", user.getEmail())
        .claim("role", user.getRole())
        .claim("user_id", user.getId())
        .expiration(Date.from(now.plus(accessTokenLifetime, ChronoUnit.SECONDS)))
        .signWith(getSecret())
        .compact();
    return new TokenResponse(accessToken);
  }

  public String getUserEmailFromToken(String token) {
    try {
      var payload = Jwts
          .parser()
          .verifyWith(getSecret())
          .build()
          .parseSignedClaims(token)
          .getPayload();

      return (String) payload.get("email");
    } catch (Exception e) {
      return null;
    }
  }

  @Builder
  public static record TokenResponse(String accessToken) {
  }
}

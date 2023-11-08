package ukma.edu.ua.HospitalApp.shared;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTService {

  @Autowired
  private JwtConfig jwtConfig;

  public String generateToken(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
            .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
            .compact();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser()
            .setSigningKey(jwtConfig.getSecret())
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token);
      return true;
    } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
      return false;
    }
  }
}

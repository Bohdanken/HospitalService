package ukma.edu.ua.HospitalApp.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {
  public UnauthorizedException() {
    super(HttpStatus.UNAUTHORIZED);
  }
}

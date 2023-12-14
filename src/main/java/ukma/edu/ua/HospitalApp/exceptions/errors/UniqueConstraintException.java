package ukma.edu.ua.HospitalApp.exceptions.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UniqueConstraintException extends ResponseStatusException {
  public UniqueConstraintException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}

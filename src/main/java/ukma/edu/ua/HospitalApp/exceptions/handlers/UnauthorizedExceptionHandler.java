package ukma.edu.ua.HospitalApp.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ukma.edu.ua.HospitalApp.exceptions.errors.UnauthorizedException;

@RestControllerAdvice
public class UnauthorizedExceptionHandler {
  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<Void> handleNotFoundException(UnauthorizedException ex) {
    return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
  }
}

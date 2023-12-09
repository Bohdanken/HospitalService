package ukma.edu.ua.HospitalApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class BadRequestExceptionHandler {
  @ExceptionHandler(ResponseStatusException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseError> handleResponseStatusException(ResponseStatusException ex) {
    var error = new ResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
  }
}

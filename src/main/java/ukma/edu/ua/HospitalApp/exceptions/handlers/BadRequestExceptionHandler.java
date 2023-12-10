package ukma.edu.ua.HospitalApp.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ukma.edu.ua.HospitalApp.exceptions.ResponseError;
import ukma.edu.ua.HospitalApp.exceptions.errors.BadRequestException;

@RestControllerAdvice
public class BadRequestExceptionHandler {
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseError> handleBadRequestException(BadRequestException ex) {
    var error = new ResponseError(ex.getReason(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
  }
}

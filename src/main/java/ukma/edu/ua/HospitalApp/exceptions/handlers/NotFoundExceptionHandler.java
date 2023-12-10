package ukma.edu.ua.HospitalApp.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ukma.edu.ua.HospitalApp.exceptions.ResponseError;
import ukma.edu.ua.HospitalApp.exceptions.errors.NotFoundException;

@RestControllerAdvice
public class NotFoundExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ResponseError> handleNotFoundException(NotFoundException ex) {
    var error = new ResponseError(ex.getReason(), HttpStatus.NOT_FOUND);
    return new ResponseEntity<ResponseError>(error, HttpStatus.NOT_FOUND);
  }
}

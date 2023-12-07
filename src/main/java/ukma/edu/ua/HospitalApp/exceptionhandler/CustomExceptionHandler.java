package ukma.edu.ua.HospitalApp.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseError handleConflict1(IncorrectIDException ex) {
    return new ResponseError(ex.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseError handleConflict2(IncorrectBodyException ex) {
    return new ResponseError(ex.getMessage() + "\n" + ex.getErrors(), HttpStatus.CONFLICT);
  }
}

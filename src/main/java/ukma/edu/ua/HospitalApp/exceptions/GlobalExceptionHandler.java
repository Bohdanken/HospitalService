package ukma.edu.ua.HospitalApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({ NotFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ResponseError> handleNotFoundException(ResponseStatusException e) {
    var error = new ResponseError(e.getReason(), HttpStatus.NOT_FOUND);
    return new ResponseEntity<ResponseError>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ BadRequestException.class, UniqueConstraintException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseError> handleBadRequestException(ResponseStatusException e) {
    var error = new ResponseError(e.getReason(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ UnauthorizedException.class })
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<Void> handleUnauthorizedException(UnauthorizedException e) {
    // TODO: separate into 2 handlers for 401 and 403
    return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
  }
}

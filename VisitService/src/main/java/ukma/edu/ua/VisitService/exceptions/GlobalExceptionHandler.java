package ukma.edu.ua.VisitService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseError> handleArgumentNotValidException(MethodArgumentNotValidException e) {
    var message = "Field \"" + e.getFieldErrors().get(0).getField() + "\" "
        + e.getFieldErrors().get(0).getDefaultMessage();
    var error = new ResponseError(message, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ HttpMessageNotReadableException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseError> handleMessageNotReadableException(HttpMessageNotReadableException e) {
    var err = new ResponseError("Message not readable", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<ResponseError>(err, HttpStatus.BAD_REQUEST);
  }
}

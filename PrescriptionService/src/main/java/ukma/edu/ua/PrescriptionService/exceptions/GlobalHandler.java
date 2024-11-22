package ukma.edu.ua.PrescriptionService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
	@ExceptionHandler({ BadRequestException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ResponseError> handleNotFoundException(BadRequestException e) {
		var err = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ResponseError>(err, HttpStatus.BAD_REQUEST);
	}
}

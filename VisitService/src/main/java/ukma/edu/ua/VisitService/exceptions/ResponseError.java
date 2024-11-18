package ukma.edu.ua.VisitService.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@ToString
public class ResponseError {
  private final HttpStatus status;

  private final String message;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private final LocalDateTime time = LocalDateTime.now();

  public ResponseError(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }

  public ResponseError(String message, HttpStatusCode status) {
    this.message = message;
    this.status = HttpStatus.valueOf(status.value());
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public LocalDateTime getTime() {
    return time;
  }
}

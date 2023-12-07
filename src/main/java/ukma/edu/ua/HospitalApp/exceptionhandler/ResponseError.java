package ukma.edu.ua.HospitalApp.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ResponseError {
  private final HttpStatus status;

  private final String message;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime time = LocalDateTime.now();

  public ResponseError(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
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

  @Override
  public String toString() {
    return "ResponseError{" +
        "status=" + status +
        ", message='" + message + '\'' +
        ", time=" + time +
        '}';
  }
}

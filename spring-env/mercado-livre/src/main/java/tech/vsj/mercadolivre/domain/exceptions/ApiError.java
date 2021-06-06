package tech.vsj.mercadolivre.domain.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiError {

  private HttpStatus httpStatus;
  private LocalDateTime timestamp;
  private String message;
  private String debugMessage;
  private List<ApiSubError> subError;

  public ApiError() {
    this.timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus httpStatus) {
    this();
    this.httpStatus = httpStatus;
  }

  public ApiError(HttpStatus httpStatus, Throwable ex) {
    this();
    this.httpStatus = httpStatus;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  public ApiError(HttpStatus httpStatus, String message, Throwable ex) {
    this();
    this.httpStatus = httpStatus;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDebugMessage() {
    return debugMessage;
  }

  public List<ApiSubError> getSubError() {
    return subError;
  }

}

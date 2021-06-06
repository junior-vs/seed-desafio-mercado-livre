package tech.vsj.mercadolivre.domain.exceptions;

public class ApiValidationError extends ApiError {

  private String object;
  private String filed;
  private Object rejectValue;
  private String message;

  public ApiValidationError(String object, String filed, Object rejectValue, String message) {
    super();
    this.object = object;
    this.filed = filed;
    this.rejectValue = rejectValue;
    this.message = message;
  }

  public ApiValidationError(String object, String message) {
    super();
    this.object = object;
    this.message = message;
  }

  public String getObject() {
    return object;
  }

  public String getFiled() {
    return filed;
  }

  public Object getRejectValue() {
    return rejectValue;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return String.format("ApiValidationError [object=%s, filed=%s, rejectValue=%s, message=%s]",
        object, filed, rejectValue, message);
  }

}

package cz.deriva.commons.exceptions;


public abstract class AbstractApplicationException extends RuntimeException {

  private final String exceptionType;
  private String code;
  private Throwable reason;
  private String message;

  public AbstractApplicationException(String exceptionType, String code, Throwable e) {
    this.exceptionType = exceptionType;
    this.code = code;
    this.reason = e;
  }

  public AbstractApplicationException(String exceptionType, String code) {
    this.exceptionType = exceptionType;
    this.code = code;
  }

  public AbstractApplicationException(String exceptionType) {
    this.exceptionType = exceptionType;
  }

  public AbstractApplicationException(String exceptionType, String code, String message) {
    this.exceptionType = exceptionType;
    this.code = code;
    this.message = message;
  }

  public String getExceptionType() {
    return exceptionType;
  }

  public Throwable getReason() {
    return reason;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}

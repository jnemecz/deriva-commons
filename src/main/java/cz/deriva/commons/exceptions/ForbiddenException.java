package cz.deriva.commons.exceptions;

public final class ForbiddenException extends AbstractApplicationException {

  public ForbiddenException() {
    super(BaseExceptionTypes.AUTHORIZATION_ERROR, BaseExceptionTypes.INSUFFICIENT_SYSTEM_PERMISSION);
  }

  public ForbiddenException(final String message) {
    super(BaseExceptionTypes.AUTHORIZATION_ERROR, BaseExceptionTypes.INSUFFICIENT_SYSTEM_PERMISSION, message);
  }

}

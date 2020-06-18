package cz.deriva.commons.exceptions;

public class InvalidStateException extends AbstractApplicationException {

  public InvalidStateException(String message) {
    super(BaseExceptionTypes.APPLICATION_ERROR, BaseExceptionCodes.INVALID_STATE, message);
  }

  public InvalidStateException() {
    super(BaseExceptionTypes.APPLICATION_ERROR, BaseExceptionCodes.INVALID_STATE);
  }
}

package cz.deriva.commons.exceptions;

public class NotSupportedOperationException extends AbstractApplicationException {

  public NotSupportedOperationException() {
    super(BaseExceptionTypes.APPLICATION_ERROR, BaseExceptionCodes.NOT_SUPPORTED_OPERATION_EXCEPTION);
  }

  public NotSupportedOperationException(String message) {
    super(BaseExceptionTypes.APPLICATION_ERROR, BaseExceptionCodes.NOT_SUPPORTED_OPERATION_EXCEPTION, message);
  }

}

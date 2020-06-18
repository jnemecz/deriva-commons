package cz.deriva.commons.exceptions;

public class ProcessingDataErrorException extends AbstractApplicationException {

  public ProcessingDataErrorException(String message) {
    super(BaseExceptionTypes.APPLICATION_ERROR, BaseExceptionCodes.DATA_PROCESSING_ERROR, message);
  }

}

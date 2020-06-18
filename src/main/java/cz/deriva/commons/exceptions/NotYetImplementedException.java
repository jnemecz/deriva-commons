package cz.deriva.commons.exceptions;

public class NotYetImplementedException extends AbstractApplicationException {

  public NotYetImplementedException() {
    super(BaseExceptionTypes.APPLICATION_ERROR, BaseExceptionCodes.NOT_IMPLEMENTED_ERROR);
  }

}

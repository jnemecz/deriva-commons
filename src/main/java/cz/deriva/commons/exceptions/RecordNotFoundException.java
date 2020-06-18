package cz.deriva.commons.exceptions;


public class RecordNotFoundException extends AbstractApplicationException {

  private final Object identifier;

  public RecordNotFoundException(final String code) {
    this(code, null);
  }

  public RecordNotFoundException(final String code, final Object identifier) {
    super(BaseExceptionTypes.ENTITY_ERROR, code);
    this.identifier = identifier;
  }

  public Object getIdentifier() {
    return identifier;
  }

}

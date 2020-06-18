package cz.deriva.commons.exceptions;


import org.springframework.validation.Errors;

public final class ValidationSpringException extends AbstractApplicationException {

  private final Errors errors;

  public ValidationSpringException(Errors errors) {
    super(BaseExceptionTypes.VALIDATION_ERROR);
    this.errors = errors;
  }

  public Errors getErrors() {
    return errors;
  }
}

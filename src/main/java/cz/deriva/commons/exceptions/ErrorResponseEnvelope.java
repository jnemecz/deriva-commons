package cz.deriva.commons.exceptions;


import org.springframework.util.Assert;

public final class ErrorResponseEnvelope extends MyResponseEntity {

  public ErrorResponseEnvelope(ErrorResponse errorResponse) {
    Assert.notNull(errorResponse, "errorResponse");
    this.put("error", errorResponse);
  }

}


package cz.deriva.commons.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.deriva.commons.transformation.JsonIdSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ErrorResponse {

  @JsonProperty("type")
  private final String type;

  @JsonProperty("code")
  private final String code;

  @JsonProperty("field")
  private String fieldName;

  @JsonProperty("identifier")
  @JsonSerialize(using = JsonIdSerializer.class)
  private Object value;

  public ErrorResponse(final String exceptionType, final String code) {
    this.type = exceptionType;
    this.code = code;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getType() {
    return type;
  }

  public String getCode() {
    return code;
  }

}


package cz.deriva.commons.exceptions;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.deriva.commons.transformation.JsonResponseCustomSerializer;
import org.springframework.util.Assert;

import java.util.HashMap;

/**
 * Obalka pro vraceni hodnoty zpet na klienta.
 */
public class MyResponseEntity {

  private final HashMap<String, Object> content = new HashMap<>();

  public static MyResponseEntity of(final String fieldName, final Object value) {
    MyResponseEntity response = new MyResponseEntity();
    response.put(fieldName, value);
    return response;
  }

  public void put(String name, Object value) {
    Assert.notNull(name, "Name should not be null");
    Assert.notNull(value, "Value should not be null");
    this.content.put(name, value);
  }

  @JsonValue
  @JsonSerialize(using = JsonResponseCustomSerializer.class)
  public HashMap<String, Object> getContent() {
    return content;
  }

}


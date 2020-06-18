package cz.deriva.commons.transformation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import cz.deriva.commons.data.domain.RecordId;

import java.io.IOException;
import java.util.UUID;

public final class JsonIdSerializer extends JsonSerializer {

  @Override
  public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

    if (value instanceof String) {

      gen.writeString(String.valueOf(value));

    } else if (value instanceof RecordId) {

      RecordId v = (RecordId) value;
      gen.writeNumber(v.value());

    } else if (value instanceof UUID) {

      UUID v = (UUID) value;
      gen.writeString(v.toString());

    } else {
      gen.writeObject(value);
    }

  }

}

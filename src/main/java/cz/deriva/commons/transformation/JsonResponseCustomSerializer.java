package cz.deriva.commons.transformation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class JsonResponseCustomSerializer extends JsonSerializer<Object> {

  @Override
  public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws
      IOException {

    HashMap<String, Object> map = (HashMap<String, Object>) value;
    gen.writeStartObject();

    Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      gen.writeObjectField(pair.getKey().toString(), pair.getValue());
      it.remove();
    }

    gen.writeEndObject();

  }

}

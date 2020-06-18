package cz.deriva.commons.transformation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cz.deriva.commons.utils.AssertUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jirka on 2019-09-17.
 *
 * @author Jiri Nemec
 */
public final class ObjectMapperInitializator {

  public static ObjectMapper get() {
    return ObjectMapperInitializator.get(new ObjectMapper());
  }

  public static ObjectMapper get(final ObjectMapper objectMapper) {

    AssertUtils.notNull(objectMapper, "objectMapper");

    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    final SimpleModule zonedDateTimeModul = new SimpleModule();
    zonedDateTimeModul.addSerializer(ZonedDateTime.class, new ZoneDateTimeWithoutTimezoneString());
    objectMapper.registerModule(zonedDateTimeModul);

    final SimpleModule localDateTimeModul = new SimpleModule();
    localDateTimeModul.addDeserializer(LocalTime.class, new MyLocalDateTimeParser());
    objectMapper.registerModule(localDateTimeModul);

    final SimpleModule localDateParserModule = new SimpleModule();
    localDateParserModule.addDeserializer(LocalDate.class, new LocalDateParser());
    objectMapper.registerModule(localDateParserModule);

    final SimpleModule zonedTimeDateSerializerModule = new SimpleModule();
    zonedTimeDateSerializerModule.addDeserializer(ZonedDateTime.class, new ZoneDateTimeWithoutTimezoneStringDeserializer());
    objectMapper.registerModule(zonedTimeDateSerializerModule);

    return objectMapper;

  }

  public static class ZoneDateTimeWithoutTimezoneStringDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      final String val = p.getValueAsString();
      return ZonedDateTime.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(val));
    }

  }

  private static class LocalDateParser extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
      return LocalDate.parse(p.getValueAsString().replaceAll("\\+\\d{4}$", ""), DateTimeFormatter.ISO_DATE);
    }

  }

  private static class MyLocalDateTimeParser extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

      String[] time = p.getValueAsString().split(":");

      if (time.length == 1) {
        return LocalTime.of(Integer.valueOf(time[0]), 0);
      } else if (time.length == 2) {
        return LocalTime.of(Integer.valueOf(time[0]), Integer.valueOf(time[1]));
      } else {
        throw new RuntimeException(String.format("Nepodporovany casovy zaznam: %s", p.getValueAsString()));
      }
    }

  }

  private static class ZoneDateTimeWithoutTimezoneString extends JsonSerializer {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      ZonedDateTime zdt = (ZonedDateTime) value;
      gen.writeString(zdt.toOffsetDateTime().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

  }


}

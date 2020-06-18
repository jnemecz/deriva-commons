package cz.deriva.commons.transformation;

import cz.deriva.commons.utils.DateTimeUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Converter
public final class ZonedDateTimedAttributeConverter implements AttributeConverter<ZonedDateTime, java.sql.Timestamp> {

  @Override
  public Timestamp convertToDatabaseColumn(final ZonedDateTime attribute) {
    return DateTimeUtils.toSqlTimeStamp(attribute);
  }

  @Override
  public ZonedDateTime convertToEntityAttribute(final Timestamp dbData) {
    return DateTimeUtils.toZoneDateTime(dbData, ZoneOffset.UTC.getId());
  }

}

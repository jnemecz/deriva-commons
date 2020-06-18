package cz.deriva.commons.transformation;

import cz.deriva.commons.data.domain.RecordId;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Constructor;

public abstract class AbstractIdDatabaseAttributeConverter<T extends RecordId> implements AttributeConverter<T, Long> {

  private final Class<T> targetClass;

  protected AbstractIdDatabaseAttributeConverter(Class<T> targetClass) {
    this.targetClass = targetClass;
  }

  public Long convertToDatabaseColumn(T attribute) {
    if (attribute != null) {
      return attribute.value();
    }
    return null;
  }

  @Override
  public T convertToEntityAttribute(Long dbData) {
    if (dbData != null) {
      try {
        return tc(dbData);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      return null;
    }
  }

  protected T tc(Long dbData) throws Exception {
    Constructor<?> ctor = targetClass.getConstructor(Long.class);
    T object = (T) ctor.newInstance(new Object[]{dbData});
    return object;
  }

}

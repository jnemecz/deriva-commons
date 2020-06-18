package cz.deriva.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class ReflectionUtils {

  /**
   * <p>Nastavuje {@code private} {@code final} {@code static} field na danou hodnotu {@code value}</p>
   *
   * @param clazz     trida ve ktere se ma hodnota zmenit
   * @param fieldName nazev parametru, ktery se ma menit
   * @param value     nova hodnota parametru
   * @throws ReflectiveOperationException
   */
  public static void setFinalStaticField(Class<?> clazz, String fieldName, Object value) {

    try {

      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      Field modifiers = Field.class.getDeclaredField("modifiers");
      modifiers.setAccessible(true);
      modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
      field.set(null, value);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
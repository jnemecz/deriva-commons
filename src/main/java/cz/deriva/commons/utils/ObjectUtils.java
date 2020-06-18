package cz.deriva.commons.utils;

import java.math.BigDecimal;

/**
 * Created by jirka on 20.06.18.
 *
 * @author Jiri Nemec
 */
public class ObjectUtils {

  private ObjectUtils() {
  }

  public static BigDecimal safeZero(final BigDecimal bigDecimal) {
    if (bigDecimal == null) {
      return BigDecimal.ZERO;
    }
    return bigDecimal;
  }

  public static BigDecimal safeBigDecimal(final String value) {

    if (StringUtils.isBlank(value)) {
      return null;
    } else {

      try {
        return new BigDecimal(value);
      } catch (Exception e) {
        return null;
      }

    }

  }

  public static Long safeLong(final String value) {

    if (StringUtils.isBlank(value)) {
      return null;
    } else {

      try {
        return Long.valueOf(value);
      } catch (Exception e) {
        return null;
      }

    }

  }

  public static Integer safeInteger(final String value) {

    if (StringUtils.isBlank(value)) {
      return null;
    } else {

      try {
        return Integer.valueOf(value);
      } catch (Exception e) {
        return null;
      }

    }

  }

}

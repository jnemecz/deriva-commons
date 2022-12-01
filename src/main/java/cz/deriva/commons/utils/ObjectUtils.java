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

  public static Double safeDouble(final String value) {
    return ObjectUtils.safeDouble(value, null);
  }

  public static Double safeDouble(final String value, final Double fallBackValue) {

    if (StringUtils.isBlank(value)) {
      return fallBackValue;
    } else {
      try {
        return Double.valueOf(value);
      } catch (Exception e) {
        return fallBackValue;
      }
    }

  }

  public static BigDecimal safeBigDecimal(final BigDecimal value, final BigDecimal fallBackValue) {

    if (value != null) {
      return value;
    } else {
      return fallBackValue;
    }

  }

  public static BigDecimal safeBigDecimal(final String value, final BigDecimal fallBackValue) {
    if (StringUtils.isBlank(value)) {
      return fallBackValue;
    } else {

      try {
        return new BigDecimal(value);
      } catch (Exception e) {
        return fallBackValue;
      }

    }
  }

  public static BigDecimal safeBigDecimal(final String value) {
    return ObjectUtils.safeBigDecimal(value, null);
  }

  public static Long safeLong(final String value, final Long fallBackValue) {
    if (StringUtils.isBlank(value)) {
      return fallBackValue;
    } else {

      try {
        return Long.valueOf(value);
      } catch (Exception e) {
        return fallBackValue;
      }

    }
  }

  public static Long safeLongZero(final String value) {
    return ObjectUtils.safeLong(value, 0L);
  }

  public static Long safeLong(final String value) {
    return ObjectUtils.safeLong(value, null);
  }

  public static Integer safeInteger(final String value, Integer fallbackValue) {

    if (StringUtils.isBlank(value)) {
      return fallbackValue;
    } else {

      try {
        return Integer.valueOf(value);
      } catch (Exception e) {
        return fallbackValue;
      }

    }
  }

  public static Integer safeInteger(final String value) {
    return ObjectUtils.safeInteger(value, null);
  }

  public static String safeToString(Object object) {
    if (object == null) {
      return null;
    }
    return object.toString();
  }

}

package cz.deriva.commons;

import java.math.BigDecimal;

/**
 * Created by jirka on 20.06.18.
 *
 * @author Jiri Nemec
 */
public class ComparisonUtils {

  private ComparisonUtils() {
  }

  /**
   * <p>Kontroluje jestli se dve hodnoty rovnaji.</p>
   */
  public static boolean equals(final BigDecimal value1, final BigDecimal value2) {
    if (value1 != null && value2 != null) {
      return value1.compareTo(value2) == 0;
    } else {
      return false;
    }
  }


  public static <T> boolean sameWithNull(final T object1, final T object2) {
    if (object1 != null && object2 == null) {
      return false;
    } else {
      return object1 == null && object2 != null ? false : object1.equals(object2);
    }
  }

}

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
    if (value1 == null || value2 == null) {
      return false;
    }
    return value1.compareTo(value2) == 0;
  }

}

package cz.deriva.commons.utils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public final class ValidationUtils {

  private final static Double DOUBLE_ZERO = 0d;

  private ValidationUtils() {
  }

  public static boolean isGtZero(final String value, final Class clazz) {
    AssertUtils.notNull(clazz, "clazz");

    if (StringUtils.isBlank(value)) {
      return false;
    }

    if (clazz == Long.class) {
      return ValidationUtils.isGtZero(ObjectUtils.safeLong(value, 0L));

    } else if (clazz == Double.class) {
      return ValidationUtils.isGtZero(ObjectUtils.safeDouble(value, 0d));

    } else if (clazz == Integer.class) {
      return ValidationUtils.isGtZero(ObjectUtils.safeInteger(value, 0));

    } else {
      throw new IllegalArgumentException(String.format("Unsupported Type: %s", clazz));
    }

  }

  public static boolean isGtZero(final Double value) {
    if (value == null) {
      return false;
    }
    return Double.compare(value, DOUBLE_ZERO) > 0;
  }

  /**
   * Kontroluje, jestli jsou seznamy shodne bez ohledu na poradi hodnot.
   *
   * @param list1 prvni kontrolovany seznam
   * @param list2 druhy kontrolovany seznam
   * @param <T>   typ hodnot v seznamu
   *
   * @return {@code true} pokud jsou shodne, jinak {@code false}
   */
  public static <T> boolean listEqualsIgnoreOrder(final List<T> list1, final List<T> list2) {

    if ((list1 == null && list2 != null) || (list1 != null && list2 == null)) {
      return false;
    }

    if (list1 == null && list2 == null) {
      return false;
    }

    return new HashSet<>(list1).equals(new HashSet<>(list2));
  }

  public static boolean isSame(final BigDecimal value1, final BigDecimal value2) {

    if (value1 == null || value2 == null) {
      return false;
    }

    if (value1.getClass() != BigDecimal.class || value2.getClass() != BigDecimal.class) {
      return false;
    }

    return value1.compareTo(value2) == 0;

  }

  public static boolean isZero(final BigDecimal value) {
    if (value == null) {
      return false;
    } else {
      return value.compareTo(BigDecimal.ZERO) == 0;
    }
  }

  public static boolean isGteZero(final Double value) {
    if (value == null) {
      return false;
    }
    return Double.compare(value, DOUBLE_ZERO) >= 0;
  }

  public static boolean isGteZero(final Long value) {
    if (value == null) {
      return false;
    }
    return value >= 0L;
  }

  public static boolean isGtZero(final Integer value) {
    if (value == null) {
      return false;
    }
    return value > 0;
  }

  public static boolean isGtZero(final Long value) {
    if (value == null) {
      return false;
    }
    return value > 0;
  }

  /**
   * @param value         konrolovana hodnota
   * @param fromInclusive hodnota zacatku intervalu (vcetne)
   * @param toInclusive   hodnota konce intervalu (vcetne)
   *
   * @return true pokud je hodnota v danem rozmezi, jinak false
   */
  public static boolean between(final BigDecimal value, final BigDecimal fromInclusive, final BigDecimal toInclusive) {

    // nektera hodnota neni vyplnena - neni co kontrolovat
    if (value == null || fromInclusive == null || toInclusive == null) {
      return false;
    }

    // Konec intervalu neni vetsi nebo roven zacatku
    if (!ValidationUtils.isGte(toInclusive, fromInclusive)) {
      return false;
    }

    // Kontrolvoana hodnota neni vetsi nebo rovna zacatku intervalu
    if (!ValidationUtils.isGte(value, fromInclusive)) {
      return false;
    }

    // Kontrolvoana hodnota neni mensi nebo rovna konci intervalu
    return ValidationUtils.isLte(value, toInclusive);

  }

  public static boolean isGteZero(final BigDecimal fieldValue) {
    if (fieldValue == null) {
      return false;
    }
    return fieldValue.compareTo(BigDecimal.ZERO) >= 0;
  }

  public static boolean isGte(final BigDecimal fieldValue, final BigDecimal checkValue) {
    if (fieldValue == null || checkValue == null) {
      return false;
    }
    return fieldValue.compareTo(checkValue) >= 0;
  }

  public static boolean isGt(final BigDecimal fieldValue, final BigDecimal checkValue) {
    if (fieldValue == null || checkValue == null) {
      return false;
    }
    return fieldValue.compareTo(checkValue) == 1;
  }

  public static boolean isLte(final BigDecimal fieldValue, final BigDecimal checkValue) {
    if (fieldValue == null || checkValue == null) {
      return false;
    }
    return fieldValue.compareTo(checkValue) <= 0;
  }

  public static boolean isLtZero(final BigDecimal fieldValue) {
    return ValidationUtils.isLt(fieldValue, BigDecimal.ZERO);
  }

  public static boolean isLteZero(final BigDecimal fieldValue) {
    return ValidationUtils.isLte(fieldValue, BigDecimal.ZERO);
  }

  public static boolean isLt(final BigDecimal fieldValue, final BigDecimal checkValue) {
    if (fieldValue == null || checkValue == null) {
      return false;
    }
    return fieldValue.compareTo(checkValue) == -1;
  }

  public static boolean isGtZero(final BigDecimal fieldValue) {
    if (fieldValue == null) {
      return false;
    }
    return fieldValue.compareTo(BigDecimal.ZERO) == 1;
  }

  public static boolean isValidEmail(final CharSequence email) {

    if (StringUtils.isBlank(email)) {
      return false;
    }

    final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
    java.util.regex.Matcher m = p.matcher(email);
    return m.matches();
  }

  /**
   * <p>Kontroluje, jestli hesslo splnuje security policy.</p>
   *
   * <p>Pokud je heslo potvrzeno jako bezpecne a je vraceno {@code true}, je mozne
   * heslo povolit v systemu protoze splnuje pozadavky na (uzivatelsky-relativne)
   * bezpecne heslo.</p>
   *
   * @param passwordValue kontrolovane heslo
   *
   * @return true pokud je heslo bezpecne, jinak false
   *
   * @see <a href="https://support.apple.com/en-us/HT201303">Security and your Apple ID</a>
   */
  public static boolean isValidPassword(final CharSequence passwordValue) {

    if (StringUtils.isBlank(passwordValue)) {
      return false;
    }

    final String password = passwordValue.toString();

    boolean matched = true;

    if (matched && !Pattern.compile("^[a-zA-Z-_'.0-9]{8,64}$").matcher(password).find()) {
      matched = false;
    }

    if (matched && !password.matches("(.*[0-9].*)")) { // alespo jedna cislice
      matched = false;
    }

    if (matched && !password.matches("(.*[A-Z].*)")) { // alespon jedno velke pismeno
      matched = false;
    }

    if (matched && !password.matches("(.*[a-z].*)")) { // alespon jedno male pismeno
      matched = false;
    }

    return matched;
  }

}

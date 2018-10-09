package cz.deriva.commons;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public final class ValidationUtils {

  private final static BigDecimal BIGDECIMAL_MINUS_ONE = BigDecimal.valueOf(-1);

  private ValidationUtils() {
  }

  public static boolean isGteZero(final BigDecimal fieldValue) {
    return isGreater(fieldValue, BIGDECIMAL_MINUS_ONE);
  }

  public static boolean isGtZero(final BigDecimal fieldValue) {
    return isGreater(fieldValue, BigDecimal.ZERO);
  }

  public static boolean isValidEmail(final CharSequence email) {
    // TODO: dodelat validaci!
    return StringUtils.isNotBlank(email);
  }

  /**
   * <p>Kontroluje, jestli hesslo splnuje security policy.</p>
   *
   * <p>Pokud je heslo potvrzeno jako bezpecne a je vraceno {@code true}, je mozne
   * heslo povolit v systemu protoze splnuje pozadavky na (uzivatelsky-relativne)
   * bezpecne heslo.</p>
   *
   * @param passwordValue kontrolovane heslo
   * @return true pokud je heslo bezpecne, jinak false
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

    if (matched && !password.matches("(.*[0-9].*)")) {
      matched = false;
    }

    if (matched && !password.matches("(.*[A-Z].*)")) {
      matched = false;
    }

    return matched;
  }

  /**** PRIVATE STUFF *******************************************************************/

  private static boolean isGreater(BigDecimal value, BigDecimal minimumValue) {
    if (value == null) {
      return false;
    }
    return value.compareTo(minimumValue) == 1;
  }

}

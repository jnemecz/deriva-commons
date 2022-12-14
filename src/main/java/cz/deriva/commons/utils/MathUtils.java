package cz.deriva.commons.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jirka on 20.06.18.
 *
 * @author Jiri Nemec
 */
public class MathUtils {

  private final static Random NOT_SECURE_RANDOM = new Random();

  private MathUtils() {
  }


  /**
   * Zaokrhlouje na danou hodnotu
   *
   * @param value
   * @param increment
   * @param roundingMode
   * @return see: https://stackoverflow.com/a/16665378/1315357
   */
  public static BigDecimal round(
      BigDecimal value,
      BigDecimal increment,
      RoundingMode roundingMode
  ) {

    AssertUtils.notNull(value, "value");
    AssertUtils.notNull(increment, "increment");
    AssertUtils.notNull(roundingMode, "roundingMode");

    if (increment.signum() == 0) {
      // 0 increment does not make much sense, but prevent division by 0
      return value;
    } else {
      BigDecimal divided = value.divide(increment, 0, roundingMode);
      BigDecimal result = divided.multiply(increment);
      return result;
    }
  }

  public static BigDecimal divide(final BigDecimal value1, final BigDecimal value2, int decimals) {
    return value1.divide(value2, decimals, BigDecimal.ROUND_HALF_UP);
  }

  public static BigDecimal divide(final BigDecimal value1, final BigDecimal value2) {
    return MathUtils.divide(value1, value2, 5);
  }

  public static int getNumberOfDecimalPlaces(final BigDecimal bigDecimal) {
    AssertUtils.notNull(bigDecimal, "bigDecimal");
    String string = bigDecimal.stripTrailingZeros().toPlainString();
    int index = string.indexOf(".");
    return index < 0 ? 0 : string.length() - index - 1;
  }


  public static BigDecimal round(final BigDecimal value, int places) {
    AssertUtils.validState((places > 0 && places < 6), "Pocet desetinnych mist musi byt <1;5>");
    BigDecimal result = value.setScale(places, RoundingMode.HALF_UP);
    return result;
  }

  public static double round(final double value, int places) {
    AssertUtils.validState((places > 0 && places < 6), "Pocet desetinnych mist musi byt <1;5>");
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  /**
   * <p>Vraci nahododnou ciselnou hodnotu v danem rozmezi.</p>
   *
   * @param low  minimalni hodnota (vcetne)
   * @param high maximalni generovana hodnota (vcetne)
   * @return hodnota v danem rozmezi
   */
  public static int randomInRange(int low, int high) {
    if (high < low) {
      throw new IllegalArgumentException("low <= high");
    }
    return ThreadLocalRandom.current().nextInt(low, high + 1);
  }

}

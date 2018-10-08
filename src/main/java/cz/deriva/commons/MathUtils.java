package cz.deriva.commons;

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

  public static double round(double value, int places) {
    AssertUtils.validState(places < 0, "Min. 1 desetinne misto");
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

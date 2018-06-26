package cz.deriva.commons;

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

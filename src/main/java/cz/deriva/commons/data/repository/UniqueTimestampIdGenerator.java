package cz.deriva.commons.data.repository;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generator ID podle casove znacky, vysledne hodnoty jsou raditelne dle casoveho poradi.
 * <p>
 * Generuje 100 id za 1 milisekundu (ms).
 * <p>
 * Upraveno + credits: https://stackoverflow.com/a/28371817/1315357
 */
public final class UniqueTimestampIdGenerator implements IdGenerationStrategy {

  private static final AtomicLong TS_LONG = new AtomicLong();
  private static final AtomicInteger TS_INTEGER = new AtomicInteger();

  public Long nextValue() {
    return getUniqueTimestamp();
  }

  @Override
  public Integer nextIntValue() {
    return getUniqueIntegerTimestamp();
  }

  /**** PRIVATE STUFF *******************************************************************/

  private long getUniqueTimestamp() {

    // Aktualni cas v ms
    long micros = System.currentTimeMillis() * 100;

    while (true) {
      // Aktualni hodnota
      long value = TS_LONG.get();
      if (micros <= value) {
        micros = value + 1;
      }
      if (TS_LONG.compareAndSet(value, micros)) {
        return micros;
      }
    }

  }

  private int getUniqueIntegerTimestamp() {

    int micros = new Long(System.currentTimeMillis()).intValue();

    int value;
    do {
      value = TS_INTEGER.get();
      if (micros <= value) {
        micros = value + 1;
      }
    } while (!TS_INTEGER.compareAndSet(value, micros));

    return micros;

  }

}

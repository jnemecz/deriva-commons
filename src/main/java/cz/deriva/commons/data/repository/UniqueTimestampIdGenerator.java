package cz.deriva.commons.data.repository;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Generator ID podle casove znacky, vysledne hodnoty jsou raditelne dle casoveho poradi.
 * <p>
 * Generuje 100 id za 1 milisekundu (ms).
 * <p>
 * Upraveno + credits: https://stackoverflow.com/a/28371817/1315357
 */
public final class UniqueTimestampIdGenerator implements IdGenerationStrategy {

  private static final AtomicLong TS = new AtomicLong();

  public Long nextValue() {
    return getUniqueTimestamp();
  }

  /**** PRIVATE STUFF *******************************************************************/

  private long getUniqueTimestamp() {

    // Aktualni cas v ms
    long micros = System.currentTimeMillis() * 100;

    while (true) {
      // Aktualni hodnota
      long value = TS.get();
      if (micros <= value) {
        micros = value + 1;
      }
      if (TS.compareAndSet(value, micros)) {
        return micros;
      }
    }

  }

}

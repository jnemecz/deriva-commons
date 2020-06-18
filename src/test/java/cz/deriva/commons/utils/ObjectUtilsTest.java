package cz.deriva.commons.utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jirka on 2020-03-28.
 *
 * @author Jiri Nemec
 */
class ObjectUtilsTest {

  @Nested
  class SafeBigDecimal {

    @Test
    public void testNull() {

      assertAll(
          () -> assertNull(ObjectUtils.safeBigDecimal(null)),
          () -> assertNull(ObjectUtils.safeBigDecimal("")),
          () -> assertNull(ObjectUtils.safeBigDecimal("  ")),
          () -> assertNull(ObjectUtils.safeBigDecimal("deset")),
          () -> assertNull(ObjectUtils.safeBigDecimal("1,1"))
      );

    }

    @Test
    public void testCorrect() {

      assertAll(
          () -> assertEquals(BigDecimal.valueOf(0).compareTo(ObjectUtils.safeBigDecimal("0")), 0),
          () -> assertEquals(BigDecimal.valueOf(1d).compareTo(ObjectUtils.safeBigDecimal("1")), 0),
          () -> assertEquals(BigDecimal.valueOf(0.0000001d).compareTo(ObjectUtils.safeBigDecimal("0.0000001")), 0),
          () -> assertEquals(BigDecimal.valueOf(-1d).compareTo(ObjectUtils.safeBigDecimal("-1")), 0)
      );

    }

  }

  @Nested
  class SafeLong {

    @Test
    public void testNull() {

      assertAll(
          () -> assertNull(ObjectUtils.safeLong(null)),
          () -> assertNull(ObjectUtils.safeLong("")),
          () -> assertNull(ObjectUtils.safeLong("  ")),
          () -> assertNull(ObjectUtils.safeLong("deset")),
          () -> assertNull(ObjectUtils.safeLong("1,1"))
      );

    }

    @Test
    public void testCorrect() {

      assertAll(
          () -> assertEquals(Long.valueOf(0), ObjectUtils.safeLong("0")),
          () -> assertEquals(Long.valueOf(1), ObjectUtils.safeLong("1")),
          () -> assertEquals(Long.valueOf(Long.MAX_VALUE), ObjectUtils.safeLong("" + Long.MAX_VALUE + ""), "max value")
      );

    }

  }

  @Nested
  class SafeInteger {

    @Test
    public void testNull() {

      assertAll(
          () -> assertNull(ObjectUtils.safeInteger(null)),
          () -> assertNull(ObjectUtils.safeInteger("")),
          () -> assertNull(ObjectUtils.safeInteger("  ")),
          () -> assertNull(ObjectUtils.safeInteger("deset")),
          () -> assertNull(ObjectUtils.safeInteger("1,1"))
      );

    }

    @Test
    public void testCorrect() {

      assertAll(
          () -> assertEquals(Integer.valueOf(0), ObjectUtils.safeInteger("0")),
          () -> assertEquals(Integer.valueOf(1), ObjectUtils.safeInteger("1")),
          () -> assertEquals(Integer.valueOf(Integer.MAX_VALUE), ObjectUtils.safeInteger("" + Integer.MAX_VALUE + ""), "max value")
      );

    }

  }

}
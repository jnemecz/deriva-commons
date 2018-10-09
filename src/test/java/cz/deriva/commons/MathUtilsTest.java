package cz.deriva.commons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
class MathUtilsTest {

  @Nested
  class Round {

    @Test
    public void test1() {

      final BigDecimal v = new BigDecimal("0.1666666");
      final BigDecimal rest = MathUtils.round(v, 2);

      assertEquals("0.17", rest.toString());

    }

    @Test
    public void test2() {

      final BigDecimal v = new BigDecimal("0.16");
      final BigDecimal rest = MathUtils.round(v, 2);

      assertEquals("0.16", rest.toString());

    }

    @Test
    public void test3() {

      final BigDecimal v = new BigDecimal("0.000000000000001");
      final BigDecimal rest = MathUtils.round(v, 2);

      assertEquals("0.00", rest.toString());

    }

    @Test
    public void test4() {

      final BigDecimal v = new BigDecimal("0.001");
      final BigDecimal rest = MathUtils.round(v, 2);

      assertEquals("0.00", rest.toString());

    }
    @Test
    public void test5() {

      final BigDecimal v = new BigDecimal("0");
      final BigDecimal rest = MathUtils.round(v, 2);

      assertEquals("0.00", rest.toString());

    }

  }

  @Nested
  class RandomInteger {

    @Test
    @DisplayName("Test same range 10-10")
    public void testSameRange() {
      testLoop(50_000, 10, 10);
    }

    @Test
    @DisplayName("Test illegal values")
    public void testSameRange2() {
      assertThrows(IllegalArgumentException.class,
          () -> MathUtils.randomInRange(10, 9)
      );
    }

    @Test
    @DisplayName("Test range 10-11")
    public void test() {
      testLoop(50_000, 10, 11);
    }

    private void testLoop(int repetition, int low, int high) {
      for (int i = 0; i < repetition; i++) {
        final int random = MathUtils.randomInRange(low, high);
        checkRandom(random, low, high);
      }
    }

    private void checkRandom(int random, int low, int high) {
      assertTrue(random >= low && random <= high);
    }


  }

}
package cz.deriva.commons;

import cz.deriva.commons.utils.ValidationUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

  @Nested
  class Between {

    @Test
    public void test1() {

      final BigDecimal from = new BigDecimal("1");
      final BigDecimal to = new BigDecimal("2");
      final BigDecimal val = new BigDecimal("1.9");

      assertTrue(ValidationUtils.between(val, from, to));

    }

    @Test
    public void fromInclusive() {

      final BigDecimal from = new BigDecimal("1");
      final BigDecimal to = new BigDecimal("2");
      final BigDecimal val = new BigDecimal("1");

      assertTrue(ValidationUtils.between(val, from, to));

    }

    @Test
    public void toInclusive() {

      final BigDecimal from = new BigDecimal("1");
      final BigDecimal to = new BigDecimal("2");
      final BigDecimal val = new BigDecimal("2");

      assertTrue(ValidationUtils.between(val, from, to));

    }

    @Test
    public void toInclusiveNotBetween() {

      final BigDecimal from = new BigDecimal("1");
      final BigDecimal to = new BigDecimal("2");
      final BigDecimal val = new BigDecimal("2.1");

      assertFalse(ValidationUtils.between(val, from, to));

    }

    @Test
    public void fromInclusiveNotBetween() {

      final BigDecimal from = new BigDecimal("1");
      final BigDecimal to = new BigDecimal("2");
      final BigDecimal val = new BigDecimal("0.9");

      assertFalse(ValidationUtils.between(val, from, to));

    }


    @Test
    public void okInputs() {

      assertAll(
          () -> assertDoesNotThrow(() -> ValidationUtils.between(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE), "bezchybne vstupy"),
          () -> assertDoesNotThrow(() -> ValidationUtils.between(null, BigDecimal.ZERO, BigDecimal.ONE), "bezchybne vstupy"),
          () -> assertDoesNotThrow(() -> ValidationUtils.between(BigDecimal.ZERO, null, BigDecimal.ONE), "bezchybne vstupy"),
          () -> assertDoesNotThrow(() -> ValidationUtils.between(BigDecimal.ZERO, BigDecimal.ZERO, null), "bezchybne vstupy"),
          () -> assertDoesNotThrow(() -> ValidationUtils.between(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ONE), "interval od = do")
      );

    }

  }

}
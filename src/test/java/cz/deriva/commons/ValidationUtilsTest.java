package cz.deriva.commons;

import cz.deriva.commons.utils.ValidationUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

  @Nested
  class ListEqualsIgnoreOrder {

    @Test
    public void test1() {


      final List<Integer> list1 = Arrays.asList(1, 2, 3);
      final List<Integer> list2 = Arrays.asList(1, 2, 3, 4);

      assertFalse(ValidationUtils.listEqualsIgnoreOrder(list1, list2));

    }

    @Test
    public void test2() {

      final List<Integer> list1 = Arrays.asList(1, 2, 3);
      final List<Integer> list2 = Arrays.asList(1, 2, 3);

      assertTrue(ValidationUtils.listEqualsIgnoreOrder(list1, list2));

    }

    @Test
    public void testEmpty() {

      final List<Integer> list1 = new ArrayList<>();
      final List<Integer> list2 = new ArrayList<>();

      assertTrue(ValidationUtils.listEqualsIgnoreOrder(list1, list2));

    }

    @Test
    public void testEmpty1() {

      final List<String> list1 = Arrays.asList("");
      final List<String> list2 = Arrays.asList("");

      assertTrue(ValidationUtils.listEqualsIgnoreOrder(list1, list2));

    }

    @Test
    public void testEmpty2() {

      final List<String> list2 = Arrays.asList();
      assertFalse(ValidationUtils.listEqualsIgnoreOrder(null, list2));

    }
    @Test
    public void testEmpty3() {
      assertFalse(ValidationUtils.listEqualsIgnoreOrder(null, null));
    }

  }

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
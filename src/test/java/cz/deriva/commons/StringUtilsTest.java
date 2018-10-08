package cz.deriva.commons;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jirka on 12.07.18.
 *
 * @author Jiri Nemec
 */
class StringUtilsTest {

    @Nested
    class IsEmpty {

        @Test
        public void testNotEmpty() {
            assertAll(
                    () -> assertFalse(StringUtils.isEmpty(" ")),
                    () -> assertFalse(StringUtils.isEmpty("bob")),
                    () -> assertFalse(StringUtils.isEmpty("  bob  "))
            );
        }

        @Test
        public void testEmpty() {
            assertAll(
                    () -> assertTrue(StringUtils.isEmpty(null)),
                    () -> assertTrue(StringUtils.isEmpty(""))
            );
        }

    }

    @Nested
    class IsBlank {

        @Test
        public void testNotBlank() {
            assertAll(
                    () -> assertFalse(StringUtils.isBlank("bob")),
                    () -> assertFalse(StringUtils.isBlank("  bob  "))
            );
        }

        @Test
        public void testBlank() {
            assertAll(
                    () -> assertTrue(StringUtils.isBlank(null)),
                    () -> assertTrue(StringUtils.isBlank("")),
                    () -> assertTrue(StringUtils.isBlank(" ")),
                    () -> assertTrue(StringUtils.isBlank("\n")),
                    () -> assertTrue(StringUtils.isBlank("\t")),
                    () -> assertTrue(StringUtils.isBlank("\r"))
            );
        }

    }


}
package cz.deriva.commons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
class MathUtilsTest {

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
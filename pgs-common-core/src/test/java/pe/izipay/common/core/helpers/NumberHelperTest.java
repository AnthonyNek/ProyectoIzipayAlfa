package pe.izipay.common.core.helpers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NumberHelperTest {
    @Test
    void testIsGreaterThan() {
        Integer value1 = 1;
        assertFalse(NumberHelper.isGreaterThan(value1, 1));
    }

    @Test
    void testIsGreaterThan2() {
        assertTrue(NumberHelper.isGreaterThan(42, 1));
    }

    @Test
    void testIsGreaterThan3() {
        assertTrue(NumberHelper.isGreaterThan((byte) 'A', 1));
    }

    @Test
    void testIsGreaterThan4() {
        assertFalse(NumberHelper.isGreaterThan((byte) 0, 1));
    }

    @Test
    void testIsGreaterThan5() {
        assertFalse(NumberHelper.isGreaterThan(null, 1));
    }

    @Test
    void testIsLessThan() {
        Integer value1 = 1;
        assertFalse(NumberHelper.isLessThan(value1, 1));
    }

    @Test
    void testIsLessThan2() {
        Integer value1 = 0;
        assertTrue(NumberHelper.isLessThan(value1, 1));
    }

    @Test
    void testIsLessThan3() {
        assertFalse(NumberHelper.isLessThan((byte) 'A', 1));
    }

    @Test
    void testIsLessThan4() {
        assertTrue(NumberHelper.isLessThan((byte) 0, 1));
    }

    @Test
    void testIsLessThan5() {
        assertFalse(NumberHelper.isLessThan(null, 1));
    }

    @Test
    void testIsGreaterThanOrEquals() {
        Integer value1 = 1;
        assertTrue(NumberHelper.isGreaterThanOrEquals(value1, 1));
    }

    @Test
    void testIsGreaterThanOrEquals2() {
        Integer value1 = 0;
        assertFalse(NumberHelper.isGreaterThanOrEquals(value1, 1));
    }

    @Test
    void testIsGreaterThanOrEquals3() {
        assertTrue(NumberHelper.isGreaterThanOrEquals((byte) 'A', 1));
    }

    @Test
    void testIsGreaterThanOrEquals4() {
        assertFalse(NumberHelper.isGreaterThanOrEquals((byte) 0, 1));
    }

    @Test
    void testIsGreaterThanOrEquals5() {
        assertFalse(NumberHelper.isGreaterThanOrEquals(null, 1));
    }

    @Test
    void testIsLessThanOrEquals() {
        Integer value1 = 1;
        assertTrue(NumberHelper.isLessThanOrEquals(value1, 1));
    }

    @Test
    void testIsLessThanOrEquals2() {
        assertFalse(NumberHelper.isLessThanOrEquals(42, 1));
    }

    @Test
    void testIsLessThanOrEquals3() {
        assertFalse(NumberHelper.isLessThanOrEquals((byte) 'A', 1));
    }

    @Test
    void testIsLessThanOrEquals4() {
        assertTrue(NumberHelper.isLessThanOrEquals((byte) 0, 1));
    }

    @Test
    void testIsLessThanOrEquals5() {
        assertFalse(NumberHelper.isLessThanOrEquals(null, 1));
    }

    @Test
    void testIsEquals() {
        Integer value1 = 1;
        assertTrue(NumberHelper.isEquals(value1, 1));
    }

    @Test
    void testIsEquals2() {
        Integer value1 = 0;
        assertFalse(NumberHelper.isEquals(value1, 1));
    }

    @Test
    void testIsEquals3() {
        assertFalse(NumberHelper.isEquals((byte) 'A', 1));
    }

    @Test
    void testIsEquals4() {
        assertTrue(NumberHelper.isEquals((byte) 1, 1));
    }

    @Test
    void testIsEquals5() {
        assertFalse(NumberHelper.isEquals(null, 1));
    }

    @Test
    void testIsDistinct() {
        Integer value1 = 1;
        assertFalse(NumberHelper.isDistinct(value1, 1));
    }

    @Test
    void testIsDistinct2() {
        Integer value1 = 0;
        assertTrue(NumberHelper.isDistinct(value1, 1));
    }

    @Test
    void testIsDistinct3() {
        assertTrue(NumberHelper.isDistinct((byte) 'A', 1));
    }

    @Test
    void testIsDistinct4() {
        assertFalse(NumberHelper.isDistinct(null, 1));
    }

    @Test
    void testIsDistinct5() {
        assertFalse(NumberHelper.isDistinct(1L, 1));
    }
}


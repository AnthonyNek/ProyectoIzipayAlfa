package pe.izipay.common.core.actions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

class PasswordAnalyzerTest {
    @Test
    void testHasUniqueCharsLessThan() {
        assertFalse((new PasswordAnalyzer("42")).hasUniqueCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("")).hasUniqueCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("42")).hasUniqueCharsLessThan(0));
        assertFalse((new PasswordAnalyzer("42")).hasUniqueCharsLessThan(2));
        assertTrue((new PasswordAnalyzer("42")).hasUniqueCharsLessThan(52));
        assertTrue((new PasswordAnalyzer("4242")).hasUniqueCharsLessThan(1));
    }

    @Test
    void testHasRepeatCharsGreaterThan() {
        assertFalse((new PasswordAnalyzer("42")).hasRepeatCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("")).hasRepeatCharsGreaterThan(1));
        assertTrue((new PasswordAnalyzer("42")).hasRepeatCharsGreaterThan(0));
        assertFalse((new PasswordAnalyzer("42")).hasRepeatCharsGreaterThan(-1));
    }

    @Test
    void testHasCharsGreaterThan() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertTrue(passwordAnalyzer.hasCharsGreaterThan(1, predicate));
        verify(predicate, atLeast(1)).test(any());
    }

    @Test
    void testHasCharsGreaterThan2() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertFalse(passwordAnalyzer.hasCharsGreaterThan(1, predicate));
    }

    @Test
    void testHasCharsGreaterThan3() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertFalse(passwordAnalyzer.hasCharsGreaterThan(2, predicate));
        verify(predicate, atLeast(1)).test(any());
    }

    @Test
    void testHasCharsGreaterThan4() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertFalse(passwordAnalyzer.hasCharsGreaterThan(-1, predicate));
    }

    @Test
    void testHasCharsGreaterThan5() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(false);
        assertFalse(passwordAnalyzer.hasCharsGreaterThan(1, predicate));
        verify(predicate, atLeast(1)).test(any());
    }

    @Test
    void testHasUpperCharsGreaterThan() {
        assertFalse((new PasswordAnalyzer("42")).hasUpperCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("Value")).hasUpperCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("")).hasUpperCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("42")).hasUpperCharsGreaterThan(-1));
        assertTrue((new PasswordAnalyzer("Value")).hasUpperCharsGreaterThan(0));
    }

    @Test
    void testHasLowerCharsGreaterThan() {
        assertFalse((new PasswordAnalyzer("42")).hasLowerCharsGreaterThan(1));
        assertTrue((new PasswordAnalyzer("Value")).hasLowerCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("")).hasLowerCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("42")).hasLowerCharsGreaterThan(-1));
    }

    @Test
    void testHasDigitCharsGreaterThan() {
        assertTrue((new PasswordAnalyzer("42")).hasDigitCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("Value")).hasDigitCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("")).hasDigitCharsGreaterThan(1));
        assertFalse((new PasswordAnalyzer("42")).hasDigitCharsGreaterThan(-1));
    }

    @Test
    void testHasCharsLessThan() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertFalse(passwordAnalyzer.hasCharsLessThan(1, predicate));
        verify(predicate).test(any());
    }

    @Test
    void testHasCharsLessThan2() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertFalse(passwordAnalyzer.hasCharsLessThan(1, predicate));
    }

    @Test
    void testHasCharsLessThan3() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertFalse(passwordAnalyzer.hasCharsLessThan(0, predicate));
    }

    @Test
    void testHasCharsLessThan4() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertFalse(passwordAnalyzer.hasCharsLessThan(2, predicate));
        verify(predicate, atLeast(1)).test(any());
    }

    @Test
    void testHasCharsLessThan5() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertTrue(passwordAnalyzer.hasCharsLessThan(6, predicate));
        verify(predicate, atLeast(1)).test(any());
    }

    @Test
    void testHasCharsLessThan6() {
        PasswordAnalyzer passwordAnalyzer = new PasswordAnalyzer("42");
        Predicate<Character> predicate = (Predicate<Character>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(false);
        assertTrue(passwordAnalyzer.hasCharsLessThan(1, predicate));
        verify(predicate, atLeast(1)).test(any());
    }

    @Test
    void testHasUpperCharsLessThan() {
        assertTrue((new PasswordAnalyzer("42")).hasUpperCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("Value")).hasUpperCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("")).hasUpperCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("42")).hasUpperCharsLessThan(0));
        assertTrue((new PasswordAnalyzer("Value")).hasUpperCharsLessThan(5));
    }

    @Test
    void testHasLowerCharsLessThan() {
        assertTrue((new PasswordAnalyzer("42")).hasLowerCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("Value")).hasLowerCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("")).hasLowerCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("42")).hasLowerCharsLessThan(0));
        assertTrue((new PasswordAnalyzer("Value")).hasLowerCharsLessThan(5));
    }

    @Test
    void testHasDigitCharsLessThan() {
        assertFalse((new PasswordAnalyzer("42")).hasDigitCharsLessThan(1));
        assertTrue((new PasswordAnalyzer("Value")).hasDigitCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("")).hasDigitCharsLessThan(1));
        assertFalse((new PasswordAnalyzer("42")).hasDigitCharsLessThan(0));
        assertFalse((new PasswordAnalyzer("42")).hasDigitCharsLessThan(2));
    }
}


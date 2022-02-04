package pe.izipay.common.core.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TextAnalyzerHelperTest {
    @Test
    void testCountRepeatChars() {
        assertEquals(0, TextAnalyzerHelper.countRepeatChars("42"));
        assertEquals(2, TextAnalyzerHelper.countRepeatChars("foo"));
        assertEquals(0, TextAnalyzerHelper.countRepeatChars(""));
    }
}


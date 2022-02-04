package pe.izipay.common.core.helpers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class RegexHelperTest {
    @Test
    void testReadPattern() {
        assertEquals("42", RegexHelper.readPattern("42", (byte) 0, ".*"));
        assertNull(RegexHelper.readPattern("42", (byte) 'A', "U"));
    }

    @Test
    void testHasPattern() {
        assertTrue(RegexHelper.hasPattern("42", ".*"));
        assertFalse(RegexHelper.hasPattern("42", "U"));
    }

    @Test
    void testHasPatternSafe() {
        assertTrue(RegexHelper.hasPatternSafe("42", ".*"));
        assertFalse(RegexHelper.hasPatternSafe("42", "U"));
    }

    @Test
    void testReadPatterns() {
        List<String> actualReadPatternsResult = RegexHelper.readPatterns("42", (byte) 0, ".*");
        assertEquals(2, actualReadPatternsResult.size());
        assertEquals("42", actualReadPatternsResult.get(0));
        assertEquals("", actualReadPatternsResult.get(1));
    }

    @Test
    void testMatch() {
        assertTrue(RegexHelper.match("40", ".*"));
        assertFalse(RegexHelper.match("40", "U"));
    }

    @Test
    void testMatchSafe() {
        assertTrue(RegexHelper.matchSafe("42", ".*"));
        assertFalse(RegexHelper.matchSafe("42", "U"));
    }
    
    @Test
    void createPatternContainsIgnoreCase() {
		var pattern = RegexHelper.createPatternContainsIgnoreCase("Test");
		
		assertThat(pattern.matcher("Project Test").find()).isTrue();
		assertThat(pattern.matcher("Project texst").find()).isFalse();
		assertThat(pattern.matcher("Project tests").find()).isTrue();
		assertThat(pattern.matcher("Project tesT").find()).isTrue();
	}
}


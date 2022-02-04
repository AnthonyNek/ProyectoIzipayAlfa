package pe.izipay.common.core.helpers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pe.izipay.common.core.FieldValidation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

@SpringBootTest
class TextAnalyzerHelperTests {

	@Test
	void anyUpperChar() {
		assertThat(TextAnalyzerHelper.anyUpperChar("123456789")).isFalse();
		assertThat(TextAnalyzerHelper.anyUpperChar("hola mundo")).isFalse();
		assertThat(TextAnalyzerHelper.anyUpperChar("HOLA MUNDO")).isTrue();
		assertThat(TextAnalyzerHelper.anyUpperChar("hola mundO")).isTrue();
		assertThat(TextAnalyzerHelper.anyUpperChar("~`!@#$%^&*()-_=+\\\\|[{]};:'\\\",<.>/?")).isFalse();
		assertThat(TextAnalyzerHelper.anyUpperChar(null)).isFalse();
		assertThat(TextAnalyzerHelper.anyUpperChar("")).isFalse();
	}

	@Test
	void anyLowerChar() {
		assertThat(TextAnalyzerHelper.anyLowerChar("123456789")).isFalse();
		assertThat(TextAnalyzerHelper.anyLowerChar("hola mundo")).isTrue();
		assertThat(TextAnalyzerHelper.anyLowerChar("HOLA MUNDO")).isFalse();
		assertThat(TextAnalyzerHelper.anyLowerChar("HoLA MUNDO")).isTrue();
		assertThat(TextAnalyzerHelper.anyLowerChar("~`!@#$%^&*()-_=+\\\\|[{]};:'\\\",<.>/?")).isFalse();
		assertThat(TextAnalyzerHelper.anyLowerChar(null)).isFalse();
		assertThat(TextAnalyzerHelper.anyLowerChar("")).isFalse();
	}

	@Test
	void countRepeatChars() {
		assertThat(TextAnalyzerHelper.countRepeatChars("GeeksaforGeeksa")).isEqualTo(12);
		assertThat(TextAnalyzerHelper.countRepeatChars("GeeksforGeeks")).isEqualTo(10);
		assertThat(TextAnalyzerHelper.countRepeatChars("GeekAforGeeka")).isEqualTo(8);
	}

	@Test
	void countUniqueChars() {
		assertThat(TextAnalyzerHelper.countUniqueChars("GeeksaforGeeksa")).isEqualTo(3);
		assertThat(TextAnalyzerHelper.countUniqueChars("GeekaforGeek")).isEqualTo(4);
		assertThat(TextAnalyzerHelper.countUniqueChars("GeekAforGeeka")).isEqualTo(5);
	}

	@Test
	void hasUniqueCharsLessThan() {
		assertThat(TextAnalyzerHelper.hasUniqueCharsLessThan("GeeksaforGeeksa", 4)).isTrue();
		assertThat(TextAnalyzerHelper.hasUniqueCharsLessThan("GeekaforGeek", 5)).isTrue();
		assertThat(TextAnalyzerHelper.hasUniqueCharsLessThan("GeekAforGeeka", 6)).isTrue();
		assertThat(TextAnalyzerHelper.hasUniqueCharsLessThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasUniqueCharsLessThan("GeeksaforGeeksa", 3)).isFalse();
		assertThat(TextAnalyzerHelper.hasUniqueCharsLessThan("GkaforGk", 4)).isFalse();
		assertThat(TextAnalyzerHelper.hasUniqueCharsLessThan("for", 2)).isFalse();
	}

	@Test
	void hasRepeatCharsGreaterThan() {
		assertThat(TextAnalyzerHelper.hasRepeatCharsGreaterThan("GeeksaforGeeksa", 3)).isTrue();
		assertThat(TextAnalyzerHelper.hasRepeatCharsGreaterThan("GkaforGk", 1)).isTrue();
		assertThat(TextAnalyzerHelper.hasRepeatCharsGreaterThan("for", 0)).isTrue();
		assertThat(TextAnalyzerHelper.hasRepeatCharsGreaterThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasRepeatCharsGreaterThan("GeeksaforGeeksa", 4)).isFalse();
		assertThat(TextAnalyzerHelper.hasRepeatCharsGreaterThan("GkaforGk", 2)).isFalse();
		assertThat(TextAnalyzerHelper.hasRepeatCharsGreaterThan("for", 1)).isFalse();
	}

	@Test
	void hasUpperCharsGreaterThan() {
		assertThat(TextAnalyzerHelper.hasUpperCharsGreaterThan("GeeksaforGeeksA", 2)).isTrue();
		assertThat(TextAnalyzerHelper.hasUpperCharsGreaterThan("GkaforGk", 1)).isTrue();
		assertThat(TextAnalyzerHelper.hasUpperCharsGreaterThan("foAr", 0)).isTrue();
		assertThat(TextAnalyzerHelper.hasUpperCharsGreaterThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasUpperCharsGreaterThan("GeeksaforGeeksa", 2)).isFalse();
		assertThat(TextAnalyzerHelper.hasUpperCharsGreaterThan("GkaforGk", 2)).isFalse();
		assertThat(TextAnalyzerHelper.hasUpperCharsGreaterThan("for", 1)).isFalse();
	}

	@Test
	void hasLowerCharsGreaterThan() {
		assertThat(TextAnalyzerHelper.hasLowerCharsGreaterThan("GeeksaforGeeksA", 11)).isTrue();
		assertThat(TextAnalyzerHelper.hasLowerCharsGreaterThan("GkaforGk", 5)).isTrue();
		assertThat(TextAnalyzerHelper.hasLowerCharsGreaterThan("for", 2)).isTrue();
		assertThat(TextAnalyzerHelper.hasLowerCharsGreaterThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasLowerCharsGreaterThan("GeeksaforGeeksa", 13)).isFalse();
		assertThat(TextAnalyzerHelper.hasLowerCharsGreaterThan("GkaforGk", 6)).isFalse();
		assertThat(TextAnalyzerHelper.hasLowerCharsGreaterThan("for", 3)).isFalse();
	}

	@Test
	void hasDigitCharsGreaterThan() {
		assertThat(TextAnalyzerHelper.hasDigitCharsGreaterThan("Gee9ks8afor1Gee1ksA", 3)).isTrue();
		assertThat(TextAnalyzerHelper.hasDigitCharsGreaterThan("Gkaf1orGk", 0)).isTrue();
		assertThat(TextAnalyzerHelper.hasDigitCharsGreaterThan("5f5or4", 2)).isTrue();
		assertThat(TextAnalyzerHelper.hasDigitCharsGreaterThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasDigitCharsGreaterThan("Geek1saforGeeksa", 1)).isFalse();
		assertThat(TextAnalyzerHelper.hasDigitCharsGreaterThan("Gk1af2orGk", 2)).isFalse();
		assertThat(TextAnalyzerHelper.hasDigitCharsGreaterThan("for", 0)).isFalse();
	}

	@Test
	void hasCharsGreaterThan() {
		Predicate<Character> predicate = FieldValidation::hasCharsEspecial;
		assertThat(TextAnalyzerHelper.hasCharsGreaterThan("Gee9/ks8*afor1.Gee1ks-A", 3, predicate)).isTrue();
		assertThat(TextAnalyzerHelper.hasCharsGreaterThan("Gkaf1or~Gk", 0, predicate)).isTrue();
		assertThat(TextAnalyzerHelper.hasCharsGreaterThan("5f5&o%r!4", 2, predicate)).isTrue();
		assertThat(TextAnalyzerHelper.hasCharsGreaterThan("f#or", -1, predicate)).isFalse();
		assertThat(TextAnalyzerHelper.hasCharsGreaterThan("Geek1saforG)eeksa", 1, predicate)).isFalse();
		assertThat(TextAnalyzerHelper.hasCharsGreaterThan("Gk1af2o]rG|k", 2, predicate)).isFalse();
		assertThat(TextAnalyzerHelper.hasCharsGreaterThan("for", 0, predicate)).isFalse();
	}

	@Test
	void hasUpperCharsFrom() {
		assertThat(TextAnalyzerHelper.hasUpperCharsLessThan("GeeksaforGeeksA", 4)).isTrue();
		assertThat(TextAnalyzerHelper.hasUpperCharsLessThan("GkaforGk", 3)).isTrue();
		assertThat(TextAnalyzerHelper.hasUpperCharsLessThan("foAr", 2)).isTrue();
		assertThat(TextAnalyzerHelper.hasUpperCharsLessThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasUpperCharsLessThan("GeeksaForGeeksa", 2)).isFalse();
		assertThat(TextAnalyzerHelper.hasUpperCharsLessThan("GkaforGk", 2)).isFalse();
		assertThat(TextAnalyzerHelper.hasUpperCharsLessThan("for", 0)).isFalse();
	}

	@Test
	void hasLowerCharsLessThan() {
		assertThat(TextAnalyzerHelper.hasLowerCharsLessThan("GeeksaforGeeksA", 14)).isTrue();
		assertThat(TextAnalyzerHelper.hasLowerCharsLessThan("GkaforGk", 7)).isTrue();
		assertThat(TextAnalyzerHelper.hasLowerCharsLessThan("for", 4)).isTrue();
		assertThat(TextAnalyzerHelper.hasLowerCharsLessThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasLowerCharsLessThan("GeeksaforGeeksa", 13)).isFalse();
		assertThat(TextAnalyzerHelper.hasLowerCharsLessThan("GkaforGk", 6)).isFalse();
		assertThat(TextAnalyzerHelper.hasLowerCharsLessThan("for", 2)).isFalse();
	}

	@Test
	void hasDigitCharsLessThan() {
		assertThat(TextAnalyzerHelper.hasDigitCharsLessThan("Gee9ks8afor1Gee1ksA", 5)).isTrue();
		assertThat(TextAnalyzerHelper.hasDigitCharsLessThan("Gkaf1orGk", 2)).isTrue();
		assertThat(TextAnalyzerHelper.hasDigitCharsLessThan("5f5or4", 4)).isTrue();
		assertThat(TextAnalyzerHelper.hasDigitCharsLessThan("for", -1)).isFalse();
		assertThat(TextAnalyzerHelper.hasDigitCharsLessThan("Geek1saforGeeksa", 1)).isFalse();
		assertThat(TextAnalyzerHelper.hasDigitCharsLessThan("Gk1af2orGk", 2)).isFalse();
		assertThat(TextAnalyzerHelper.hasDigitCharsLessThan("for", 0)).isFalse();
	}

	@Test
	void hasCharsFrom() {
		Predicate<Character> predicate = FieldValidation::hasCharsEspecial;
		assertThat(TextAnalyzerHelper.hasCharsLessThan("Gee9/ks8*afor1.Gee1ks-A", 5, predicate)).isTrue();
		assertThat(TextAnalyzerHelper.hasCharsLessThan("Gkaf1or~Gk", 2, predicate)).isTrue();
		assertThat(TextAnalyzerHelper.hasCharsLessThan("5f5&o%r!4", 4, predicate)).isTrue();
		assertThat(TextAnalyzerHelper.hasCharsLessThan("f#or", -1, predicate)).isFalse();
		assertThat(TextAnalyzerHelper.hasCharsLessThan("Geek1saforG)eeksa", 1, predicate)).isFalse();
		assertThat(TextAnalyzerHelper.hasCharsLessThan("Gk1af2o]rG|k", 2, predicate)).isFalse();
		assertThat(TextAnalyzerHelper.hasCharsLessThan("for", 0, predicate)).isFalse();
	}

	@Test
	void testAnyLowerCharIgnoreBlank() {
		assertFalse(TextAnalyzerHelper.anyLowerCharIgnoreBlank("42"));
		assertTrue(TextAnalyzerHelper.anyLowerCharIgnoreBlank("foo"));
		assertTrue(TextAnalyzerHelper.anyLowerCharIgnoreBlank("UlU"));
		assertTrue(TextAnalyzerHelper.anyLowerCharIgnoreBlank(".*[a-z].*"));
		assertTrue(TextAnalyzerHelper.anyLowerCharIgnoreBlank("Value"));
		assertTrue(TextAnalyzerHelper.anyLowerCharIgnoreBlank(""));
		assertTrue(TextAnalyzerHelper.anyLowerCharIgnoreBlank(null));
		assertTrue(TextAnalyzerHelper.anyLowerCharIgnoreBlank("42UlU"));
		assertFalse(TextAnalyzerHelper.anyLowerCharIgnoreBlank("VALUE"));
	}

	@Test
	void testAnyUpperCharIgnoreBlank(){
		assertFalse(TextAnalyzerHelper.anyUpperCharIgnoreBlank("42"));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank("FOO"));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank("uLu"));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank(".*[a-Z].*"));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank("Value"));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank("42UlU"));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank("VALUE"));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank(""));
		assertTrue(TextAnalyzerHelper.anyUpperCharIgnoreBlank(null));
		}

	@Test
	void testAnyUpperAndLowerChar() {
		assertFalse(TextAnalyzerHelper.anyUpperAndLowerChar(""));
		assertFalse(TextAnalyzerHelper.anyUpperAndLowerChar("42"));
		assertFalse(TextAnalyzerHelper.anyUpperAndLowerChar("foo"));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerChar("UlU"));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerChar("Value"));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerChar(".*[A-z].*"));
	}

	@Test
	void testAnyUpperAndLowerCharIgnoreBlank(){
		assertFalse(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank("42"));
		assertFalse(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank("O"));
		assertFalse(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank("o"));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank(""));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank(null));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank("UlU"));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank("Value"));
		assertTrue(TextAnalyzerHelper.anyUpperAndLowerCharIgnoreBlank(".*[z-A].*"));
	}
}

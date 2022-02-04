package pe.izipay.common.core.actions;

import java.util.function.Predicate;

import lombok.RequiredArgsConstructor;
import pe.izipay.common.core.helpers.TextAnalyzerHelper;

@RequiredArgsConstructor
public final class PasswordAnalyzer {	
	
	private final String value;		
	
	public boolean hasUniqueCharsLessThan(int n) {
		return TextAnalyzerHelper.hasUniqueCharsLessThan(value, n);
	}
	
	public boolean hasRepeatCharsGreaterThan(int n) {		
	    return TextAnalyzerHelper.hasRepeatCharsGreaterThan(value, n);
	}
	
	public boolean hasCharsGreaterThan(int n, Predicate<Character> predicate) {		
		return TextAnalyzerHelper.hasCharsGreaterThan(value, n, predicate);
	}
	
	public boolean hasUpperCharsGreaterThan(int n) {
		return TextAnalyzerHelper.hasUpperCharsGreaterThan(value, n);
	}
	
	public boolean hasLowerCharsGreaterThan(int n) {
		return TextAnalyzerHelper.hasLowerCharsGreaterThan(value, n);
	}
	
	public boolean hasDigitCharsGreaterThan(int n) {
		return TextAnalyzerHelper.hasDigitCharsGreaterThan(value, n);
	}
	
	public boolean hasCharsLessThan(int n, Predicate<Character> predicate) {		
		return TextAnalyzerHelper.hasCharsLessThan(value, n, predicate);
	}
	
	public boolean hasUpperCharsLessThan(int n) {
		return TextAnalyzerHelper.hasUpperCharsLessThan(value, n);
	}
	
	public boolean hasLowerCharsLessThan(int n) {
		return TextAnalyzerHelper.hasLowerCharsLessThan(value, n);
	}
	
	public boolean hasDigitCharsLessThan(int n) {
		return TextAnalyzerHelper.hasDigitCharsLessThan(value, n);
	}
}
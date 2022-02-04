package pe.izipay.common.core.helpers;

import java.util.function.Predicate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.izipay.common.core.FieldValidation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextAnalyzerHelper {			
	
	public static boolean anyUpperChar(String value) {
		return !TextHelper.isNullOrEmpty(value) && value.matches(FieldValidation.PATTERN_HAS_UPPER_CHAR);
	}
	
	public static boolean anyUpperCharIgnoreBlank(String value) {
		if(TextHelper.isNullOrEmpty(value)) {
			return true;
		}
		return value.matches(FieldValidation.PATTERN_HAS_UPPER_CHAR);
	}
	
	public static boolean anyLowerChar(String value) {
		return !TextHelper.isNullOrEmpty(value) && value.matches(FieldValidation.PATTERN_HAS_LOWER_CHAR);
	}
	
	public static boolean anyLowerCharIgnoreBlank(String value) {
		if(TextHelper.isNullOrEmpty(value)) {
			return true;
		}
		return value.matches(FieldValidation.PATTERN_HAS_LOWER_CHAR);
	}
	
	public static boolean anyUpperAndLowerChar(String value) {
		return !TextHelper.isNullOrEmpty(value)
				&& value.matches(FieldValidation.PATTERN_HAS_UPPER_CHAR)
				&& value.matches(FieldValidation.PATTERN_HAS_LOWER_CHAR);
	}
	
	public static boolean anyUpperAndLowerCharIgnoreBlank(String value) {
		if(TextHelper.isNullOrEmpty(value)) {
			return true;
		}
		return value.matches(FieldValidation.PATTERN_HAS_UPPER_CHAR)
				&& value.matches(FieldValidation.PATTERN_HAS_LOWER_CHAR);
	}
	
	public static int countRepeatChars(String value) {
		if(TextHelper.isNullOrEmpty(value)) return 0;		
		int count = 0;
		int length = value.length();
		char[] chars = value.toCharArray(); 
		for (int i = 0; i < length; i++) {
	        for (int j = 0; j < length; j++) {
	            if (chars[i] == chars[j] && i != j) {
	            	count++;
	                break;
	            }
	        }	        
	    }
	    return count;
	}
	
	public static boolean hasRepeatCharsGreaterThan(String value, int n) {
		if(TextHelper.isNullOrEmpty(value) || n < 0) return false;		
		int count; 
		int length = value.length();		
		char[] chars = value.toCharArray();
		for (int i = 0; i < length; i++) {
	        count = 0;
	        for (int j = 0; j < length; j++) {
	        	if (chars[i] == chars[j]) {
	                count++;
	                if(count > n)
	                	return true;
	            }
	        }
	    }
	    return false;
	}
	
	public static int countUniqueChars(String value) {
		if(TextHelper.isNullOrEmpty(value)) return 0;
		boolean hasRepeat;
		int count = 0; 
		int length = value.length();		
		char[] chars = value.toCharArray();
		for (int i = 0; i < length; i++) {
	        hasRepeat = false;
	        for (int j = 0; j < length; j++) {
	        	if (chars[i] == chars[j] && i != j) {
	                hasRepeat = true;
	                break;
	            }
	        }
	        if (!hasRepeat)
	            count++;
	    }
	    return count;
	}
	
	public static boolean hasUniqueCharsLessThan(String value, int n) {
		if(TextHelper.isNullOrEmpty(value) || n <= 0) return false;		
		boolean hasRepeat;
		int count = 0;
		int length = value.length();		
		char[] chars = value.toCharArray();
		for (int i = 0; i < length; i++) {
	        hasRepeat = false;
	        for (int j = 0; j < length; j++) {
	        	if (chars[i] == chars[j] && i != j) {
	                hasRepeat = true;
	                break;
	            }
	        }
	        if (!hasRepeat) {
	        	count++;
	        	if(count >= n) {
	        		return false;
	        	}
	        }
	    }
		return true;
	}
	
	private static boolean hasCharsGreaterThanMask(String value, int n, Predicate<Character> predicate) {			
		int count = 0; 
		int length = value.length();
		char[] chars = value.toCharArray();
		for (int i = 0; i < length; i++) {	        
	        if (predicate.test(chars[i])) {
	        	count++;
	        	if(count > n) {
	        		return true;
	        	}
	        }
	    }
	    return false;
	}
	
	public static boolean hasCharsGreaterThan(String value, int n, Predicate<Character> predicate) {
		if(TextHelper.isNullOrEmpty(value) || n < 0) return false;
		return hasCharsGreaterThanMask(value, n, predicate);
	}
	
	public static boolean hasUpperCharsGreaterThan(String value, int n) {
		return hasCharsGreaterThan(value, n, Character::isUpperCase);
	}
	
	public static boolean hasLowerCharsGreaterThan(String value, int n) {
		return hasCharsGreaterThan(value, n, Character::isLowerCase);
	}
	
	public static boolean hasDigitCharsGreaterThan(String value, int n) {
		return hasCharsGreaterThan(value, n, Character::isDigit);
	}
	
	public static boolean hasCharsLessThan(String value, int n, Predicate<Character> predicate) {
		if(TextHelper.isNullOrEmpty(value) || n <= 0) return false;
		return !hasCharsGreaterThanMask(value, n - 1, predicate);		
	}
	
	public static boolean hasUpperCharsLessThan(String value, int n) {
		return hasCharsLessThan(value, n, Character::isUpperCase);
	}
	
	public static boolean hasLowerCharsLessThan(String value, int n) {
		return hasCharsLessThan(value, n, Character::isLowerCase);
	}
	
	public static boolean hasDigitCharsLessThan(String value, int n) {
		return hasCharsLessThan(value, n, Character::isDigit);
	}
}

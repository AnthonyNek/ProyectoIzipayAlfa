package pe.izipay.common.core.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexHelper {

	public static String readPattern(String value, byte group, String regex) {		
		Matcher matcher = Pattern.compile(regex)
				.matcher(value);		
		if(matcher.find()) {
			return matcher.group(group);
		}
		return null;
	}
	
	public static boolean hasPattern(String value, String regex) {		
		return Pattern.compile(regex, Pattern.MULTILINE)
				.matcher(value)
				.find();
	}
	
	public static boolean hasPatternSafe(String value, String regex) {
		try {
			return hasPattern(value, regex);
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static List<String> readPatterns(String value, byte group, String regex) {		
		Matcher matcher = Pattern.compile(regex)
				.matcher(value);
		var list = new ArrayList<String>();
		while(matcher.find()) {
			list.add(matcher.group(group));
		}
		return list;
	}
	
	public static boolean match(String value, String regex) {		
		Matcher matcher = Pattern.compile(regex)
				.matcher(value);		
		return matcher.matches();
	}
	
	public static boolean matchSafe(String value, String regex) {
		try {
			return match(value, regex);
		} catch (Exception ex) {
			return false;
		}
	}	
	
	public static Pattern createPatternContainsIgnoreCase(String value) {
		return Pattern.compile(".*" + value + ".*", Pattern.CASE_INSENSITIVE);
	}
		
}

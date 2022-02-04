package pe.izipay.common.core.helpers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.izipay.common.core.exceptions.AppRuntimeException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextHelper {
	
	private static final String NUMERIC_TEXT = "0123456789";
	private static final String ALPHA_NUMERIC_TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + NUMERIC_TEXT +"abcdefghijklmnopqrstuvxyz";
	
	@SuppressWarnings("unchecked")
	public static <T> T castBasicType(String input, Class<T> cls) throws AppRuntimeException {
        if (cls.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(input);
        } else if (cls.isAssignableFrom(Boolean.class)) {
            return (T) Boolean.valueOf(input);
        } else if (cls.isAssignableFrom(Byte.class)) {
            return (T) Byte.valueOf(input);
        } else if (cls.isAssignableFrom(Short.class)) {
            return (T) Short.valueOf(input);
        } else if (cls.isAssignableFrom(Long.class)) {
            return (T) Long.valueOf(input);
        } else if (cls.isAssignableFrom(Float.class)) {
            return (T) Float.valueOf(input);
        } else if (cls.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(input);
        } else {
            throw new AppRuntimeException("Tipo de dato invalido");
        }
    }
	
	public static <T> T castBasicTypeSafe(String input, Class<T> cls, T defaultValue) {
		try {
			return castBasicType(input, cls);
		} catch (Exception ex) {
			return defaultValue;
		}
	}
	
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.length() == 0;
	}
	
	public static String toStringNullable(Object value) {
		return value == null ? "" : value.toString();
	}
	
	public static String getNullIfEmpty(String value) {
		if(value != null && value.isEmpty()) {
			value = null;
		}
		return value;
	}

	public static String removeIfEndWith(String value, String end) {
		if (value.endsWith(end)) {
			value = value.substring(0, value.lastIndexOf(end));
		}
		return value;
	}

	public static String removeIfEndWith(String value, char end) {
		if (value.charAt(value.length() - 1) == end) {
			value = value.substring(0, value.length() - 1);
		}
		return value;
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static String generate(String fromText, int n) {
		var sb = new StringBuilder(n);
		int index;
		for (var i = 0; i < n; i++) {
			index = (int) (fromText.length() * Math.random());
			sb.append(fromText.charAt(index));
		}
		return sb.toString();
	}
	
	public static String generateAlphaNumeric(int n) {		
		return generate(ALPHA_NUMERIC_TEXT, n);
	}
	
	public static String generateNumeric(int n) {		
		return generate(NUMERIC_TEXT, n);
	}

	public static int length(String value) {
		return value != null ? value.length() : 0;
	}

	public static String toBase64Encode(byte[] value) {
		return Base64.getEncoder().encodeToString(value);
	}
	
	public static String toBase64Encode(String value) {
		return toBase64Encode(value.getBytes());
	}
	
	public static byte[] toBase64Decode(String value) {
		return Base64.getDecoder().decode(value);
	}
	
	public static String toBase64DecodeString(String value) {
		return new String(toBase64Decode(value), StandardCharsets.UTF_8);
	}
	
	public static String trim(String value) {
		return value == null ? "" : value.trim();
	}
	
	public static String defaultValue(String value) {
		return value == null ? "" : value;
	}
	
	public static String generateRandomSecret() {
		return toBase64Encode(UUID.randomUUID().toString()).replace("\n", "").replace("\r", "");
	}
	
	public static String join(String separator, Object... values) {
		if(values == null || values.length == 0) {
			return "";
		}		
		var textBuilder = new StringBuilder();
		String text;
		for (Object item : values) {
			text = toStringNullable(item);			
			if(!text.isEmpty()) {
				textBuilder.append(text).append(separator);
	    	}
		}
		if(textBuilder.length() > 0) {
			textBuilder.setLength(textBuilder.length() - separator.length());
		}
    	return textBuilder.toString();
	}
	
	public static String join(String separator, Collection<?> values) {
		if(values == null || values.isEmpty()) return "";		
		var textBuilder = new StringBuilder();
		String text;
		for (Object item : values) {
			text = item.toString();			
			if(!text.isEmpty()) {
				textBuilder.append(text).append(separator);
	    	}
		}
		if(textBuilder.length() > 0) {
			textBuilder.setLength(textBuilder.length() - separator.length());
		}
    	return textBuilder.toString();
	}
}

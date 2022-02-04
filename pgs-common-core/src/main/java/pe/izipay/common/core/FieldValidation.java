package pe.izipay.common.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FieldValidation {
	public static final String PATTERN_JWT = "^([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)";
	//public static final String PATTERN_OBJECT_ID = "^[0-9a-fA-F]{24}$";
	public static final String PATTERN_OBJECT_ID = "^[0-9a-fA-F]{24}$";
	public static final String PATTERN_FIELD_ERROR_MAPPER_CODE = "\"([A-Z0-9_]+)\"";
	public static final String PATTERN_FIELD_ERROR_MAPPER_INPUT = "[a-zA-Z0-9_]+.([a-zA-Z0-9_]+):";
	public static final String PATTERN_HAS_UPPER_CHAR = ".*[A-Z].*";
	public static final String PATTERN_HAS_LOWER_CHAR = ".*[a-z].*";
	public static final String PATTERN_HAS_DIGIT_CHAR = ".*[\\d].*";
	public static final String PATTERN_HAS_ESPECIAL_CHAR = ".*[\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\]\\{\\}\\|\\;\\'\\:\\,\\.\\<\\>\\/\\?].*";
	
	public static final String ESPECIAL_CHARS = "~!@#$%^&*()-_=+[]{}|;':,.<>/?";
	
	public static boolean hasCharsEspecial(Character c) {
		return FieldValidation.ESPECIAL_CHARS.contains(c.toString());
	}
}

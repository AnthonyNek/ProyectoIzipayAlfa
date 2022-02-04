package pe.izipay.common.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class FieldErrorMapper {
		
	public static final String FIELD_REQUIRED = "FIELD_REQUIRED";
	public static final String FIELD_NOT_NULL = "FIELD_NOT_NULL";
	public static final String FIELD_TEXT_NOT_EMPTY = "FIELD_TEXT_NOT_EMPTY";
	public static final String FIELD_COLLECTION_NOT_EMPTY = "FIELD_COLLECTION_NOT_EMPTY";
	public static final String FIELD_ID_INVALID = "FIELD_ID_INVALID";
	public static final String FIELD_JWT_INVALID = "FIELD_JWT_INVALID";
	public static final String FIELD_EMAIL_INVALID = "FIELD_EMAIL_INVALID";
	public static final String FIELD_GREATER_ZERO = "FIELD_GREATER_ZERO";
	
	public static final String FIELD_PAGINATION_NOT_NULL = "FIELD_PAGINATION_NOT_NULL";
	public static final String FIELD_PAGINATION_FILTER_NOT_NULL = "FIELD_PAGINATION_FILTER_NOT_NULL";	
	public static final String FIELD_PAGINATION_INDEX_NOT_NULL = "FIELD_PAGINATION_INDEX_NOT_NULL";
	public static final String FIELD_PAGINATION_INDEX_MIN = "FIELD_PAGINATION_INDEX_MIN";
	public static final String FIELD_PAGINATION_SIZE_NOT_NULL = "FIELD_PAGINATION_SIZE_NOT_NULL";
	public static final String FIELD_PAGINATION_SIZE_MIN = "FIELD_PAGINATION_SIZE_MIN";	
	
}

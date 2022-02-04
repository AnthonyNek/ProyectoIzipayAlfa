package pe.izipay.common.core.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberHelper {
	
	public static final Number ZERO = 0;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <N extends Number & Comparable> boolean isGreaterThan(N value1, N value2) {
		try {
			return value1.compareTo(value2) > 0;
		} catch (ClassCastException ex0) {
			return value1.doubleValue() > value2.doubleValue();
		} catch (Exception ex1) {
			return false;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <N extends Number & Comparable> boolean isLessThan(N value1, N value2) {
		try {
			return value1.compareTo(value2) < 0;
		} catch (ClassCastException ex0) {
			return value1.doubleValue() < value2.doubleValue();
		} catch (Exception ex1) {
			return false;
		}		
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <N extends Number & Comparable> boolean isGreaterThanOrEquals(N value1, N value2) {
		try {
			return value1.compareTo(value2) >= 0;
		} catch (ClassCastException ex0) {
			return value1.doubleValue() >= value2.doubleValue();
		} catch (Exception ex1) {
			return false;
		}		
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <N extends Number & Comparable> boolean isLessThanOrEquals(N value1, N value2) {
		try {
			return value1.compareTo(value2) <= 0;
		} catch (ClassCastException ex0) {
			return value1.doubleValue() <= value2.doubleValue();
		} catch (Exception ex1) {
			return false;
		}
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <N extends Number & Comparable> boolean isEquals(N value1, N value2) {
		try {
			return value1.compareTo(value2) == 0;
		} catch (ClassCastException ex0) {
			return value1.doubleValue() == value2.doubleValue();
		} catch (Exception ex1) {
			return false;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <N extends Number & Comparable> boolean isDistinct(N value1, N value2) {
		try {
			return value1.compareTo(value2) != 0;
		} catch (ClassCastException ex0) {
			return value1.doubleValue() != value2.doubleValue();
		} catch (Exception ex1) {
			return false;
		}
	}
}
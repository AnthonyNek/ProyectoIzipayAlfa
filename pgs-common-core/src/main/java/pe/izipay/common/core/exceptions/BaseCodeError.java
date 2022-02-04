package pe.izipay.common.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseCodeError<T> {

	protected T code;
	protected String message;	
		
	public static class StringCodeError extends BaseCodeError<String> {
		
		public StringCodeError(String code, String message) {
			super(code, message);
		}
	}
	
	public static class IntegerCodeError extends BaseCodeError<Integer> {
		
		public IntegerCodeError(Integer code, String message) {
			super(code, message);
		}
	}

}
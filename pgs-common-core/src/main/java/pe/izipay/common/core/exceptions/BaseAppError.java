package pe.izipay.common.core.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.izipay.common.core.interfaces.errors.IError;

@Getter
@Setter
@NoArgsConstructor
public class BaseAppError<T> extends BaseCodeError<T> implements IError<T> {
	
	protected String input;

	public BaseAppError(T code, String message, String input) {
		super(code, message);
		this.input = input;
	}	
	
	public BaseAppError(T code, String message) {
		this(code, message, null);
	}
}
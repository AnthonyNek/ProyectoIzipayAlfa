package pe.izipay.common.core.exceptions;

import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

@Getter
public class CommonModuleException extends AppRuntimeException {
	
	private static final long serialVersionUID = -5313476616003057391L;
	
	protected transient IReadOnlyError<?> error;
	
	public CommonModuleException(Throwable cause, IReadOnlyError<?> error) {
		super(cause);
		this.error = error;
	}

	public CommonModuleException(String message, IReadOnlyError<?> error) {
		super(message);
		this.error = error;
	}
	
	public CommonModuleException(IReadOnlyError<?> error) {
		super();
		this.error = error;
	}


	public CommonModuleException(Throwable cause) {
		super(cause);
		this.error = null;
	}

	public CommonModuleException(String message) {
		super(message);
		this.error = null;
	}
}
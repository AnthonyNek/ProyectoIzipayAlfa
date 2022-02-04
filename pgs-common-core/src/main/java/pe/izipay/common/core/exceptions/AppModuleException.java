package pe.izipay.common.core.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

@Getter
public class AppModuleException extends AppRuntimeException implements Serializable {
	
	private static final long serialVersionUID = 2586347011151163305L;

	protected transient Collection<IReadOnlyError<?>> errors;

	public AppModuleException(Throwable cause, Collection<IReadOnlyError<?>> errors) {
		super(cause);
		this.errors = errors;
	}	
	
	public AppModuleException(Collection<IReadOnlyError<?>> errors) {
		super();
		this.errors = errors;
	}
	
	public AppModuleException(Throwable cause, IReadOnlyError<?> error) {
		super(cause);
		this.errors = convertToListError(error);
	}
	
	public AppModuleException(IReadOnlyError<?> error) {
		super();
		this.errors = convertToListError(error);
	}		
	
	public AppModuleException(IReadOnlyError<?> error, String input) {
		this(new BaseAppError<>(error.getCode(), error.getMessage(), input));
	}
	
	protected Collection<IReadOnlyError<?>> convertToListError(IReadOnlyError<?> error) {
		var listErrors = new ArrayList<IReadOnlyError<?>>(1);
		listErrors.add(error);
		return listErrors;
	}
	
	public String collectAllMessages() {
		var mensajesBuilder = new StringBuilder(); 
		for (var item : errors) {
			mensajesBuilder.append(item.getMessage()).append(", ");
		}
		if(mensajesBuilder.length() > 0) {
			mensajesBuilder.delete(mensajesBuilder.length() - 2, mensajesBuilder.length());
		}
		return mensajesBuilder.toString();
	}
}

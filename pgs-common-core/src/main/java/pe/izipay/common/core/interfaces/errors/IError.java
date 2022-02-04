package pe.izipay.common.core.interfaces.errors;

public interface IError<T> extends IReadOnlyError<T> {
		
	void setCode(T code);
	void setMessage(String message);	
	void setInput(String input);		
	
}
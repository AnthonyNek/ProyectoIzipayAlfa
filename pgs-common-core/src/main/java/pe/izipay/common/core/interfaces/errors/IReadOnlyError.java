package pe.izipay.common.core.interfaces.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface IReadOnlyError<T> {
	
	@JsonProperty("codigo")
	T getCode();

	@JsonProperty("mensaje")
	String getMessage();

	@JsonProperty("entrada")
	default String getInput() {
		return null;
	}
}
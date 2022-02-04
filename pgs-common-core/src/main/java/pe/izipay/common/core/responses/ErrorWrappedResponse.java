package pe.izipay.common.core.responses;

import java.time.ZonedDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

@Getter
@Setter
public class ErrorWrappedResponse extends BaseWrappedResponse<ZonedDateTime> {
	
	@JsonProperty("errores")
	protected Collection<IReadOnlyError<?>> errors;
	
	@Getter
	@Setter
	public static class IntegerErrorResponse extends BaseWrappedResponse<ZonedDateTime> {
	
		@JsonProperty("errores")
		protected Collection<IReadOnlyError<Integer>> errors;

	}
	
	@Getter
	@Setter
	public static class StringErrorResponse extends BaseWrappedResponse<ZonedDateTime> {
		
		@JsonProperty("errores")
		protected Collection<IReadOnlyError<String>> errors;

	}
}
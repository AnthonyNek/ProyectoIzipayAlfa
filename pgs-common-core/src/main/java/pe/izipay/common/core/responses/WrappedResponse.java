package pe.izipay.common.core.responses;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrappedResponse<T> extends BaseWrappedResponse<ZonedDateTime> {
	
	@JsonProperty("datos")
	protected T data;

	public static class StringWrappedResponse extends  WrappedResponse<String> {}
}

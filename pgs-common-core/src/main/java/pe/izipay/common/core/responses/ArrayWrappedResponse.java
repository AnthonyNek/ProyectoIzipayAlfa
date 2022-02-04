package pe.izipay.common.core.responses;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrayWrappedResponse<T> extends BaseWrappedResponse<ZonedDateTime> {
	
	protected T[] data;	
}

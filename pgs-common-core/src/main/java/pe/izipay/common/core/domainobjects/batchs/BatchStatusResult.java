package pe.izipay.common.core.domainobjects.batchs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchStatusResult<T> extends BatchFailResult<T>{	
				
	private boolean exito;	
	
	public static class StringBatchStatusResult extends BatchStatusResult<String> { }
}

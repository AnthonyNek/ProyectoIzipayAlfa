package pe.izipay.common.core.domainobjects.batchs;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.domainobjects.BaseDomainObject;

@Getter
@Setter
public class BatchFailResult<T> extends BaseDomainObject<T> {
					
	private String mensaje;
	
	public static class StringBatchFailResult extends BatchFailResult<String> { }
}

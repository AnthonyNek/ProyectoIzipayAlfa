package pe.izipay.common.core.domainobjects.cruds;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;

@Getter
@Setter
public class DescribableEditPayload extends ClassifierEditPayload {
	
	@NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)	
	private String descripcion;
}
package pe.izipay.common.core.domainobjects.cruds;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;

@Getter
@Setter
public class ClassifierCreatePayload extends ActivableCreatePayload {
	
	@NotBlank(message = FieldErrorMapper.FIELD_REQUIRED)	
	private String nombre;
}
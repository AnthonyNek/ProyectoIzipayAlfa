package pe.izipay.common.core.domainobjects.cruds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;
import pe.izipay.common.core.FieldValidation;

@Getter
@Setter
public class ClassifierEditPayload extends ClassifierCreatePayload {
	
	@NotNull(message = FieldErrorMapper.FIELD_ID_INVALID)
	@Pattern(regexp = FieldValidation.PATTERN_OBJECT_ID, message = FieldErrorMapper.FIELD_ID_INVALID)
	private String id;
}
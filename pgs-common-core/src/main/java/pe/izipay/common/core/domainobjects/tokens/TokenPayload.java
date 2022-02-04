package pe.izipay.common.core.domainobjects.tokens;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;
import pe.izipay.common.core.FieldValidation;

@Getter
@Setter
public class TokenPayload {
	
	@Pattern(regexp = FieldValidation.PATTERN_JWT, message = FieldErrorMapper.FIELD_JWT_INVALID)
	private String acceso;
	@Pattern(regexp = FieldValidation.PATTERN_JWT, message = FieldErrorMapper.FIELD_JWT_INVALID)
	private String actualizacion;
}

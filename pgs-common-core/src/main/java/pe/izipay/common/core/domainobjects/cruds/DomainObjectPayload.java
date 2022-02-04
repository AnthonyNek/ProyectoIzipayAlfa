package pe.izipay.common.core.domainobjects.cruds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;
import pe.izipay.common.core.FieldValidation;
import pe.izipay.common.core.interfaces.IDomainObject;

@Getter
@Setter
public class DomainObjectPayload implements IDomainObject<String> {
	
	@NotNull(message = FieldErrorMapper.FIELD_REQUIRED)
	@Pattern(regexp = FieldValidation.PATTERN_OBJECT_ID, message = FieldErrorMapper.FIELD_ID_INVALID)
	private String id;

	@Override
	public int hashCode() {
		return hashCodeId();
	}

	@Override
	public boolean equals(Object obj) {
		return equalsId(obj);
	}	
}
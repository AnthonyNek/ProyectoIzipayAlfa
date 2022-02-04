package pe.izipay.common.core.domainobjects.cruds;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;
import pe.izipay.common.core.helpers.DomainHelper;
import pe.izipay.common.core.interfaces.activables.IActivableWriter;

@Getter
@Setter
public class ActivableCreatePayload implements IActivableWriter {
		
	@JsonIgnore
	private byte estado;
	@NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)
	private Boolean activo;
	
	@Override
	public void setActivo(Boolean activo) {
		this.activo = activo;
		DomainHelper.setActivo(this, activo);
	}
}
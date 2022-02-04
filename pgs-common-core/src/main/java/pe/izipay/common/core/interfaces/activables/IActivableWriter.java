package pe.izipay.common.core.interfaces.activables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pe.izipay.common.core.helpers.DomainHelper;

public interface IActivableWriter {
    
	@JsonIgnore
	void setEstado(byte estado);
    	
	default void setActivo(Boolean activo) {
		DomainHelper.setActivo(this, activo);
	}
	
	
}
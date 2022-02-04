package pe.izipay.common.core.interfaces.activables;

import pe.izipay.common.core.types.DataStatusType;

public interface IActivableReader {
    
	byte getEstado();
    
    default boolean isActivo() {
    	return getEstado() == DataStatusType.ENABLED.ordinal();
    }
}
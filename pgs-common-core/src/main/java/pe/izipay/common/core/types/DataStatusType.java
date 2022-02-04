package pe.izipay.common.core.types;

import pe.izipay.common.core.interfaces.IType;

public enum DataStatusType implements IType {
	NONE,
	ENABLED, 	// 1 => Para registros habilitados
	DISABLED, 	// 2 => Para registros desabilitados 	
	DELETED; 	// 3 => Para registros eliminados bajo la estrategia de eliminacion logica			
	
	public static DataStatusType convert(Boolean value) {
		return value.booleanValue() ? DataStatusType.ENABLED : DataStatusType.DISABLED ;  
	}
	
	public static int convertToNumber(Boolean value) {
		if(value == null) {
			return 0;
		}		
		return (value.booleanValue() ? DataStatusType.ENABLED : DataStatusType.DISABLED).ordinal() ;  
	}		
}

package pe.izypay.pgs.mstemplate.domain.types;

import pe.izipay.common.core.interfaces.errors.IFeatureType;

public enum FeatureType implements IFeatureType {
	
	NONE,
	DEFAULT,
	LOGIN,
	PARAMETROS_GENERALES,
	POLITICAS,
	EXCEPCION_LOGIN
}

package pe.izipay.pgs.core.domain.types;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IErrorType;
import pe.izipay.common.core.interfaces.errors.IFeatureType;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.json.ReadOnlyErrorSerializer;

import java.util.Map;

@Getter
@JsonSerialize(using = ReadOnlyErrorSerializer.class)
public enum PGSCoreErrorType implements IErrorType.IErrorTypeString, IFeatureType {

	CUENTAS_TITULAR_NO_ENCONTRADO(FeatureType.CUENTAS, "No existe el titular"),
	CUENTA_NO_ENCONTRADO(FeatureType.CUENTAS, "No existe la cuenta"),
	CUENTA_NO_ENVIADA(FeatureType.CUENTAS, "Cuenta vacía"),
	;

	private static final int FROM_ID = 1000;

	private static final Map<String, IReadOnlyError<?>>[] FEATURES = IFeatureType
			.splitErrorsByFeatures(FeatureType.values(), values(), FeatureType.NONE);

	private final FeatureType feature;
	private final String message;
	private final int id;
	private final String input;

	public static Map<String, IReadOnlyError<?>>[] getFEATURES() {
		return FEATURES;
	}
	
	PGSCoreErrorType(FeatureType feature, String message, String input) {
		this.feature = feature;
		this.message = message + '.';
		this.input = input;
		this.id = FROM_ID + ordinal();
	}

	PGSCoreErrorType(FeatureType feature, String message) {
		this(feature, message, null);
	}

	@Override
	public String getCode() {
		return this.name();
	}
}

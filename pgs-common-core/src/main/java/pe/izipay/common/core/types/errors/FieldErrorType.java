package pe.izipay.common.core.types.errors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IErrorType;
import pe.izipay.common.core.json.ReadOnlyErrorSerializer;

@Getter
@JsonSerialize(using = ReadOnlyErrorSerializer.class)
public enum FieldErrorType implements IErrorType.IErrorTypeString {

	FIELD_UNKNOWN(null),
	FIELD_REQUIRED("El campo es requerido"),
	FIELD_NOT_NULL("El campo no puede ser nulo"),
	FIELD_TEXT_NOT_EMPTY("El texto no puede ser vacio"),
	FIELD_COLLECTION_NOT_EMPTY("La colección de datos no puede ser vacio"),
	FIELD_ID_INVALID("El campo no es identificador valido"),
	FIELD_JWT_INVALID("El campo no es un JWT"),
	FIELD_EMAIL_INVALID("El campo no es un correo electronico valido"),
	//FIELD_TEXT_MIN(FeatureType.DEFAULT, "La longitud del texto no puede menor a "),
	//FIELD_TEXT_MAX(FeatureType.DEFAULT, "El texto no puede ser vacio"),
	//FIELD_TEXT_RANGE(FeatureType.DEFAULT, "El texto no puede ser vacio"),

	;

	private static final int FROM_ID = 500;

	private final int id;
	private final String message;

	FieldErrorType(String message) {
		this.message = message + '.';
		this.id = FROM_ID + ordinal();
	}
}
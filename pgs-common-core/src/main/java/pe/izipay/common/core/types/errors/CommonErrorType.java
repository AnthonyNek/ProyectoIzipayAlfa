package pe.izipay.common.core.types.errors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IErrorType;
import pe.izipay.common.core.json.ReadOnlyErrorSerializer;

@Getter
@JsonSerialize(using = ReadOnlyErrorSerializer.class)
public enum CommonErrorType implements IErrorType.IErrorTypeString {

	RECORD_NOT_FOUND("El registro no fue encontrado"),
	RECORD_NOT_SAVED("El registro no se pudo guardar, contactese con el administrador"),
	BATCH_UPDATE("No se pudo actualizar ningun registro, contactese con el administrador"),
	;

	private static final int FROM_ID = 200;

	private final int id;
	private final String message;

	CommonErrorType(String message) {
		this.message = message + '.';
		this.id = FROM_ID + ordinal();
	}
}
package pe.izipay.common.core.types.errors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IErrorType;
import pe.izipay.common.core.json.ReadOnlyErrorSerializer;

@Getter
@JsonSerialize(using = ReadOnlyErrorSerializer.class)
public enum GenericErrorType implements IErrorType.IErrorTypeString {

    NONE(null), // 0
    DEFAULT(null), // 1
    UNKNOWN("A ocurrido un error desconocido en la aplicación"); // 2

    private static final int FROM_ID = 0;

    private final int id;
    private final String message;

    GenericErrorType(String message) {
        this.message = message + '.';
        this.id = FROM_ID + ordinal();
    }
}
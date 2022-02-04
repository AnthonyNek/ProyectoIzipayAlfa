package pe.izipay.common.core.types.errors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IErrorType;
import pe.izipay.common.core.json.ReadOnlyErrorSerializer;

@Getter
@JsonSerialize(using = ReadOnlyErrorSerializer.class)
public enum RestErrorType implements IErrorType.IErrorTypeString {

    INTERNAL_SERVER("A ocurrido un error de aplicación inesperado"), // 10
    CATCHED_SERVER("Se ha capturado una excepción no controlada en la aplicación"), // 11
    REQUEST_CONTENT_INVALID("El contenido de la solicitud no tiene un formato valido"), // 12
    REQUEST_ENDPOINT_NOT_FOUND("El endpoint no existe"),//13
    REQUEST_ENDPOINT_NOT_IMPLEMENTED("El endpoint(Path, Http Verb) no ha sido implementado"), // 14
    REQUEST_CONTENT_TYPE_NOT_SUPPORTED("El Content-Type de la solicitud no tiene un formato valido"), //15
    REQUEST_ARGUMENTS_INVALID("Los path(s) variables y/o query param(s) de la solicitud no tienen un formato valido"); // 16

    private static final int FROM_ID = 10;

    private final int id;
    private final String message;

    RestErrorType(String message) {
        this.message = message + '.';
        this.id = FROM_ID + ordinal();
    }
}
package pe.izypay.pgs.mstemplate.domain.types;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IErrorType;
import pe.izipay.common.core.interfaces.errors.IFeatureType;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.json.ReadOnlyErrorSerializer;

@Getter
@JsonSerialize(using = ReadOnlyErrorSerializer.class)
public enum ErrorType implements IErrorType.IErrorTypeString, IFeatureType {

	FIELD_UNKNOWN(FeatureType.DEFAULT, null),
	FIELD_REQUIRED(FeatureType.DEFAULT, "El campo es requerido"),
	FIELD_NOT_NULL(FeatureType.DEFAULT, "El campo no puede ser nulo"),
	FIELD_TEXT_NOT_EMPTY(FeatureType.DEFAULT, "El texto no puede ser vacio"),	
	FIELD_COLLECTION_NOT_EMPTY(FeatureType.DEFAULT, "La colección de datos no puede ser vacio"),
	FIELD_IS_NOT_OBJECT_ID(FeatureType.DEFAULT, "El campo no es identificador valido"),
	FIELD_JWT_INVALID(FeatureType.DEFAULT, "El campo no es un JWT"),
	FIELD_EMAIL_INVALID(FeatureType.DEFAULT, "El campo no es un correo electronico valido"),
	//FIELD_TEXT_MIN(FeatureType.DEFAULT, "La longitud del texto no puede menor a "),
	//FIELD_TEXT_MAX(FeatureType.DEFAULT, "El texto no puede ser vacio"),
	//FIELD_TEXT_RANGE(FeatureType.DEFAULT, "El texto no puede ser vacio"),
		
	TOKEN_ACCESO_CREDENCIAL_INVALIDO(FeatureType.DEFAULT, "El token de acceso no esta autorizado"),
	TOKEN_ACTUALIZACION_CREDENCIAL_INVALIDO(FeatureType.DEFAULT, "El token de actualización no esta autorizado"),
	TOKEN_USUARIO_AMBIGUO(FeatureType.DEFAULT, "El Token de acceso y actualización no pertenecen al mismo usuario"),
	
	USUARIOS_CORREO_AMBIGUO(FeatureType.DEFAULT, "No es posible realizar la operación. EL correo indicado tambien esta registrado con otro usuario, Contactese con el administrador"),
	
	PARAMETROS_GENERALES_NO_EXISTE(FeatureType.PARAMETROS_GENERALES, "El parametro general utilizado en este proceso no existe, contactese con el administrador"),
	PARAMETROS_GENERALES_VALOR_FORMATO_INVALIDO(FeatureType.PARAMETROS_GENERALES, "El valor del parametro general utilizado tiene un formato invalido, contactese con el administrador"),
	PARAMETROS_GENERALES_LISTA_ITEM(FeatureType.PARAMETROS_GENERALES, "El item con el codigo %s del parametro lista % no existe"),
	PARAMETROS_GENERALES_LISTA_ITEM_FORMATO_INVALIDO(FeatureType.PARAMETROS_GENERALES, "El item con el codigo %s del parametro lista % tiene un formato invalido, contactese con el administrador"),
	PARAMETROS_GENERALES_DEFAULT_NO_PERMITIDO(FeatureType.PARAMETROS_GENERALES, "Este es un parametro de sistema y no tiene permitido la eliminación"),
	PARAMETROS_GENERALES_LISTA_ITEM_NO_EXISTE(FeatureType.PARAMETROS_GENERALES, "El Item %s del parametro lista %s no existe"),
	PARAMETROS_GENERALES_LISTA_ITEM_NO_PERMITIDO(FeatureType.PARAMETROS_GENERALES, "El Item %s del parametro lista %s es de sistema y no tiene permitido la eliminación"),	
	PARAMETROS_GENERALES_NO_ES_LISTA(FeatureType.PARAMETROS_GENERALES, "El parametro %s no es una lista"),
	
	LOGIN_CREDENCIALES_INVALIDAS(FeatureType.LOGIN, "Usuario y/o contraseña incorrecta"),
	LOGIN_SISTEMA_ID_INVALIDO(FeatureType.LOGIN, "El sistema indicado no existe(1, 6)"),
	LOGIN_PERFIL_ENTIDAD_ID_INVALIDO(FeatureType.LOGIN, "La entidad del perfil indicado no existe(1, 6)"),
	LOGIN_SISTEMA_TOKEN_NULL_BLANK(FeatureType.LOGIN, "El token asignado es nulo o vacio"),
	LOGIN_SISTEMA_AUTENTICADOR_NULL(FeatureType.LOGIN, "El Autenticador externo no ha sido encontrado"),
	LOGIN_SESION_INVALIDA(FeatureType.LOGIN, "No tiene acceso al sistema"),
	LOGIN_SESION_MULTIFACTOR_INVALIDO(FeatureType.LOGIN, "Código de doble factor de autenticacón invalido"),
	LOGIN_CREDENCIALES_NO_CIFRADAS(FeatureType.LOGIN, "Usuario y/o contraseña no cifrada"),
	LOGIN_VIGENCIA_EXPIRADA(FeatureType.LOGIN, "Estimado usuario, su vigencia de actividades ha expirado"),	
	LOGIN_TOKEN_ACCESO_CREDENCIAL_INVALIDO(FeatureType.LOGIN, "El token de acceso no esta autorizado"),
	LOGIN_USUARIO_EXTERNO_NO_AUTORIZADO(FeatureType.LOGIN, "No existe un usuario para esta cuenta"),
	LOGIN_CUENTA_INACTIVA(FeatureType.LOGIN, "Usuario inactivo"),
	LOGIN_CUENTA_BLOQUEADA(FeatureType.LOGIN, "Usuario bloqueado"),
	LOGIN_CUENTA_BLOQUEADA_EXCESO_INTENTOS(FeatureType.LOGIN, "Usuario bloqueado por exceso de intentos"),
	
	CAMBIAR_MI_PASSWORD_INCORRECTO(FeatureType.LOGIN, "La contraseña es incorrecta"),
	CAMBIAR_MI_PASSWORD_ACTUAL_NO_EXISTE(FeatureType.LOGIN, "La contraseña actual no existe"),
	CAMBIAR_MI_PASSWORD_LDAP_NO_PERMITIDO(FeatureType.LOGIN, "No es posible cambiar la contraseña de un usuario LDAP"),
	CAMBIAR_MI_PASSWORD_FUERA_RANGO(FeatureType.LOGIN, "La contraseña debe tener entre %d y %d caracteres"),
	CAMBIAR_MI_PASSWORD_DIGITO_CHAR_REQUERIDO(FeatureType.LOGIN, "La contraseña debe tener al menos %d Dígitos"),
	CAMBIAR_MI_PASSWORD_MAYUSCULA_CHAR_REQUERIDO(FeatureType.LOGIN, "La contraseña debe tener al menos %d caracteres alfabéticos en minúsculas"),
	CAMBIAR_MI_PASSWORD_MINUSCULA_CHAR_REQUERIDO(FeatureType.LOGIN, "La contraseña debe tener al menos %d caracteres alfabéticos en MAYÚSCULAS"),
	CAMBIAR_MI_PASSWORD_ESPECIAL_CHAR_REQUERIDO(FeatureType.LOGIN, "La contraseña debe tener al menos %d caracteres especiales"),
	CAMBIAR_MI_PASSWORD_CHAR_MAXIMO_REPETICIONES(FeatureType.LOGIN, "La contraseña no puede tenes mas de %d caracteres repetidos"),
	CAMBIAR_MI_PASSWORD_CHAR_MINIMO_UNICOS(FeatureType.LOGIN, "La contraseña debe tener un minímo de %d caracteres únicos"),
	CAMBIAR_MI_PASSWORD_NUEVO_REQUERIDO(FeatureType.LOGIN, "La nueva contraseña no puede ser igual a la actual contraseña"),
	CAMBIAR_MI_PASSWORD_ANTERIOR_UTILIZADO(FeatureType.LOGIN, "No puede utilizar las contraseñas anteriores"),
	CAMBIAR_MI_PASSWORD_INSEGURO(FeatureType.LOGIN, "La nueva contraseña es insegura"),
	CAMBIAR_MI_PASSWORD_TIPO_IDENTIFICACION_NO_PERMITIDO(FeatureType.LOGIN, "Tipo de identificación no permitido en la contraseña"),
	CAMBIAR_MI_PASSWORD_NRO_IDENTIFICACION_NO_PERMITIDO(FeatureType.LOGIN, "Numero de identificación no permitido en la contraseña"),
	
	RESTABLECER_PASSWORD_CORREOS_NO_ENCONTRADOS(FeatureType.LOGIN, "El usuario seleccionado no tiene ningun correo disponible"),
	RESTABLECER_PASSWORD_CORREOS_NO_ENVIADOS(FeatureType.LOGIN, "El servidor de correos no esta enviado los email, contacte al administrador"),
	RESTABLECER_PASSWORD_NO_PERMITIDO(FeatureType.LOGIN, "No puede restablecer su contraseña, ha excedido el limite permitido en los ultimos %d dias"),
	
	ACTUALIZAR_PASSWORD_TRANSACCION_TOKEN_INVALIDO(FeatureType.LOGIN, "El token de transaccion ha expirado o es invalido", "token"),
	ACTUALIZAR_PASSWORD_TRANSACCION_USUARIO_INCORRECTO(FeatureType.LOGIN, "La transaccion no corresponde al usuario que lo solicito"),
	ACTUALIZAR_PASSWORD_TRANSACCION_INVALIDA(FeatureType.LOGIN, "El token no pertenece a la transaccion de cambio de contraseña"),
	ACTUALIZAR_PASSWORD_TRANSACCION_PASOS(FeatureType.LOGIN, "Ha omitido realizar el paso previo, que es obligatorio"),
	ACTUALIZAR_PASSWORD_RESPUESTA_PREGUNTA_NO_COINCIDEN(FeatureType.LOGIN, "La respuesta no coincide con la pregunta"),		
	
	MI_SEGURIDAD_RESPUESTA_PREGUNTA_NO_COINCIDEN(FeatureType.LOGIN, "La respuesta no coincide con la pregunta"),
	MI_SEGURIDAD_PREGUNTA_REQUERIDA(FeatureType.LOGIN, "La pregunta de seguridad es requerida"),
	MI_SEGURIDAD_RESPUESTA_PREGUNTA_SESION_EXPIRADA(FeatureType.LOGIN, "La sesión verificación de la pregunta a expirado"),	
		
	POLITICA_PREGUNTA_SEGURIDAD_NO_CONFIGURADA(FeatureType.POLITICAS, "La politica no tiene ninguna pregunta de seguridad configurada"),
	POLITICA_DOBLE_AUTENTICACION_NO_CONFIGURADA(FeatureType.POLITICAS, "La politica no tiene doble autenticación configurada"),
	POLITICA_PREGUNTA_SEGURIDAD_ACTIVA(FeatureType.POLITICAS, "La politica no tiene ninguna pregunta de seguridad activa"),
	POLITICA_PREGUNTA_SEGURIDAD_NO_PERMITIDA(FeatureType.POLITICAS, "La politica asignada al usuario no permite agregar preguntas perzonalizadas"),
	POLITICA_PREGUNTA_SEGURIDAD_MINIMO_NO_CUMPLIDO(FeatureType.POLITICAS, "La pregunta de seguridad no cumple la longitud minima"),
	POLITICA_RESPUESTA_SEGURIDAD_MINIMO_NO_CUMPLIDO(FeatureType.POLITICAS, "La respuesta de seguridad no cumple la longitud minima"),
	POLITICA_RESPUESTA_SEGURIDAD_NO_TIENE_MAYUSCULA_MINUSCULA(FeatureType.POLITICAS, "La respuesta de seguridad no tiene mayusculas y minusculas"),
	POLITICA_VIGENCIA_PASSWORD_PERIODO_DIAS(FeatureType.POLITICAS, "La politica para vigencia de contraseñas no tiene periodo de dias"),
	
	EXCEPCION_LOGIN_EXISTE_SOLICITUD_VIGENTE(FeatureType.EXCEPCION_LOGIN, "Ya existe una solicitud de excepción de doble autenticación vigente");


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
	
	ErrorType(FeatureType feature, String message, String input) {
		this.feature = feature;
		this.message = message + '.';
		this.input = input;
		this.id = FROM_ID + ordinal();
	}

	ErrorType(FeatureType feature, String message) {
		this(feature, message, null);
	}

	@Override
	public String getCode() {
		return this.name();
	}
}

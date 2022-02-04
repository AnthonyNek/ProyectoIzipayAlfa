package pe.izypay.pgs.mstemplate.infrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.izipay.common.beans.restful.RestCrudConstant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Endpoints {
	
	public static final String BEARER_SCHEME = "mef-scheme";
	
	private static final String AUTENTICACION = "/autenticacion";
	private static final String AUTENTICACION_PUBLIC = RestCrudConstant.SCOPE_PUBLIC + AUTENTICACION;
	private static final String AUTENTICACION_INTERNAL = RestCrudConstant.SCOPE_INTERNAL + AUTENTICACION;
	
	//Eliminar
	public static final String PUBLIC_LOGIN_SINGLE_SIGN_ON = AUTENTICACION_PUBLIC + "/single-sign-on";
	public static final String PUBLIC_LOGIN_MULTI_FACTOR = AUTENTICACION_PUBLIC + "/multi-factor";	
	
	public static final String AUTENTICACION_PUBLIC_INTERNO_LDAP = AUTENTICACION_PUBLIC + "/login-interno-ldap";
	public static final String AUTENTICACION_PUBLIC_GOOGLE = AUTENTICACION_PUBLIC + "/login-google";
	public static final String AUTENTICACION_PUBLIC_FACEBOOK = AUTENTICACION_PUBLIC + "/login-facebook";
	public static final String AUTENTICACION_PUBLIC_RENIEC = AUTENTICACION_PUBLIC + "/login-reniec";
	public static final String AUTENTICACION_PUBLIC_SUNAT = AUTENTICACION_PUBLIC + "/login-sunat";		
	
	public static final String AUTENTICACION_PUBLIC_RESTABLECER_MI_PASSWORD = AUTENTICACION_PUBLIC + "/restablecer-mi-password";		
	public static final String AUTENTICACION_PUBLIC_DOBLE_FACTOR = AUTENTICACION_PUBLIC + "/doble-factor";	
	public static final String AUTENTICACION_PUBLIC_SOLICITAR_PREGUNTA_SEGURIDAD = AUTENTICACION_PUBLIC + "/solicitar-pregunta-seguridad";
	public static final String AUTENTICACION_PUBLIC_VALIDAR_PREGUNTA_SEGURIDAD = AUTENTICACION_PUBLIC + "/validar-pregunta-seguridad";
	public static final String AUTENTICACION_PUBLIC_RESFRESCAR_TOKENS = AUTENTICACION_PUBLIC + "/refrescar-tokens";
	
	public static final String AUTENTICACION_INTERNAL_LOGOUT = AUTENTICACION_INTERNAL + "/logout";
	public static final String AUTENTICACION_INTERNAL_CAMBIAR_MI_PASSWORD = AUTENTICACION_INTERNAL + "/cambiar-mi-password";
	
	public static final String AUTORIZACION_INTERNAL_MENU_OPCIONES = RestCrudConstant.SCOPE_INTERNAL + "/autorizacion/menu-opciones";	
	
	public static final String DOBLE_AUTENTICACION_INTERNAL = RestCrudConstant.SCOPE_INTERNAL + "/doble-autenticacion";	
	
	public static final String PREGUNTAS_SEGURIDAD_INTERNAL = RestCrudConstant.SCOPE_INTERNAL + "/preguntas-seguridad";
	public static final String PREGUNTAS_SEGURIDAD_INTERNAL_SOLICITAR = PREGUNTAS_SEGURIDAD_INTERNAL + "/solicitar";
	public static final String PREGUNTAS_SEGURIDAD_INTERNAL_VERIFICAR = PREGUNTAS_SEGURIDAD_INTERNAL + "/verificar";
	
	public static final String EXCEPCION_DOBLE_AUTH_INTERNNAL = RestCrudConstant.SCOPE_MANAGE + "/excepcion-doble-auth";

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class SwaggerTagName {		
		public static final String AUTENTICACION = "AUTENTICACIÓN";
		public static final String AUTORIZACION = "AUTORIZACIÓN";
		public static final String PREGUNTAS_SEGURIDAD = "PREGUNTAS DE SEGURIDAD";
		public static final String DOBLE_AUTENTICACION_INTERNAL = "DOBLE AUTENTICACIÓN";
		public static final String EXCEPCION_DOBLE_AUTH = "EXCEPCION DOBLE AUTENTICACIÓN";		
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class SwaggerTagDescription {
		public static final String AUTENTICACION = "Endpoints para verificacion de credenciales de usuarios y accesos al sistema";
		public static final String AUTORIZACION = "Endpoints para autizacion de privilegios y accesos del sistema";
		public static final String PREGUNTAS_SEGURIDAD = "Endpoints de preguntas de seguridad aplicables al usuario";
		public static final String DOBLE_AUTENTICACION_INTERNAL = "Endpoints de doble autenticacion aplicables al usuario";
		public static final String EXCEPCION_DOBLE_AUTH = "Endpoints para generar excepcion de doble autenticacion";
	}
}
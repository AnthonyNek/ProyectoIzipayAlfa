package pe.izipay.common.beans.restful;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestCrudConstant {

	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String HEADER_TOKEN_PREFIX = "Bearer ";	
	
	private static final String JOIN_PARTIAL_PATH = "-";
	
	public static final String BASE_PATH_VIEW = "/view";
	public static final String BASE_PATH_DELETE = "/delete";
	
	public static final String ARG_PATH_ID = "id";
	
	public static final String ARG_NAME = "nombre";
	
	public static final String SCOPE_MANAGE = "/manage";
	public static final String SCOPE_INTERNAL = "/internal";
	public static final String SCOPE_PUBLIC = "/public";
	
	public static final String PATH_ERROR_TYPE = "/error-types";
	public static final String PATH_ERROR_TYPE_DEFINITION = PATH_ERROR_TYPE + "/definition";
	public static final String PATH_ID = "/{" + ARG_PATH_ID + "}";	
	public static final String PATH_LIST = "/listar";
	public static final String PATH_FIND = "/buscar";	
	public static final String PATH_VIEW = BASE_PATH_VIEW + PATH_ID;
	public static final String PATH_CREATE = "/create";
	public static final String PATH_EDIT = "/edit";
	public static final String PATH_DELETE = BASE_PATH_DELETE + PATH_ID;
	public static final String PATH_ENABLE = "/enable";
	public static final String PATH_FIND_PAGINATE = PATH_FIND + "-paginacion";
	
	public static final String QUERY_PARAM_NAME = "/{" + ARG_NAME + "}";
	
	public static final String PARTIAL_PATH_LIST = PATH_LIST + JOIN_PARTIAL_PATH;
	public static final String PARTIAL_PATH_FIND = PATH_FIND + JOIN_PARTIAL_PATH;
	public static final String PARTIAL_PATH_VIEW = BASE_PATH_VIEW + JOIN_PARTIAL_PATH;
	public static final String PARTIAL_PATH_CREATE = PATH_CREATE + JOIN_PARTIAL_PATH;
	public static final String PARTIAL_PATH_EDIT = PATH_EDIT + JOIN_PARTIAL_PATH;
	public static final String PARTIAL_PATH_DELETE = BASE_PATH_DELETE + JOIN_PARTIAL_PATH;
	public static final String PARTIAL_PATH_ENABLE = PATH_ENABLE + JOIN_PARTIAL_PATH;
	
	public static final String HTTP_STATUS_OK = "200";
	public static final String HTTP_STATUS_CREATED = "201";
	public static final String HTTP_STATUS_NOT_CONTENT = "204";
	public static final String HTTP_STATUS_BAD_REQUEST = "400";	
	public static final String HTTP_STATUS_UNAUTHORIZED= "401";
	public static final String HTTP_STATUS_FORBIDDEN = "403";
	public static final String HTTP_STATUS_NOT_FOUND = "404";
	public static final String HTTP_STATUS_INTERNAL_SERVER = "500";
	
	public static final String LINK_DATA_MODELS = "<b><a href=\"https://es.wikipedia.org/wiki/Modelo_de_datos\" target=\"_blank\">modelo de datos</a></b>";
	public static final String LINK_UUID = "<b><a href=\"https://es.wikipedia.org/wiki/Identificador_%C3%BAnico_universal\" target=\"_blank\">UUID</a></b>";
	
	public static final String SWAGGER_ERROR_TYPES_DEFINITION_SUMMARY = "Obtiene la collección de la definición de tipos de error manejados por el modulo";
	public static final String SWAGGER_ERROR_TYPES_DEFINITION_RESPONSE_DESCRIPTION = "La colección de la definicion tipos de error ha sido retornada satisfactoriamente";
	public static final String SWAGGER_ERROR_TYPES_LIST_SUMMARY = "Obtiene la collección de tipos de error manejados por el modulo";
	public static final String SWAGGER_ERROR_TYPES_LIST_RESPONSE_DESCRIPTION = "La colección de tipos de error ha sido retornada satisfactoriamente";
	public static final String SWAGGER_ERROR_TYPES_TAG_NAME = "TIPOS DE ERRORES";
	public static final String SWAGGER_ERROR_TYPES_TAG_DESCRIPTION = "Errores controlados por la aplicación";
	
	public static final String SWAGGER_VIEW_SUMMARY = "Obtiene los datos de un objeto del modelo de datos utilizando como parámetro de búsqueda el id";
	public static final String SWAGGER_VIEW_RESPONSE_DESCRIPTION = "El objecto del " + LINK_DATA_MODELS + " fue encontrado";
	public static final String SWAGGER_LIST_SUMMARY = "Obtiene una colección de objetos del modelo de datos con estados de registro activo";
	public static final String SWAGGER_LIST_RESPONSE_DESCRIPTION = "La colección de objectos del " + LINK_DATA_MODELS +" ha sido retornada satisfactoriamente";
	public static final String SWAGGER_LIST_FOR_FIND_SUMMARY = "Obtiene una colección de objetos del modelo de datos con estados de registro activo, inactivo";
	public static final String SWAGGER_LIST_FOR_FIND_RESPONSE_DESCRIPTION = "La colección de objectos del " + LINK_DATA_MODELS +" para la busqueda y filtrado ha sido retornada satisfactoriamente";
	public static final String SWAGGER_FIND_SUMMARY = "Obtiene una colección de objetos del modelo de datos con estados de registro no eliminados(Habilitado/Deshabilitado) y parámetros de búsquedas";
	public static final String SWAGGER_FIND_BY_NAME_SUMMARY = "Obtiene una colección de objetos del modelo de datos con estados de registro no eliminados(Habilitado/Deshabilitado) usando como parámetro de búsqueda el nombre";
	public static final String SWAGGER_FIND_RESPONSE_DESCRIPTION = "La colección de objetos del " + LINK_DATA_MODELS + " ha sido retornada satisfactoriamente";	
	public static final String SWAGGER_CREATE_SUMMARY = "Crea o agrega un nuevo registro activo al modelo de datos";
	public static final String SWAGGER_CREATE_RESPONSE_DESCRIPTION_AUTO_INCREMENT = "Retorna un valor numero entero auto-generado";
	public static final String SWAGGER_CREATE_RESPONSE_DESCRIPTION_ID= "Retorna el identificador del registro agregado";
	public static final String SWAGGER_CREATE_RESPONSE_DESCRIPTION_UUID = "Retorna un " + LINK_UUID + " representada por una cadena string";		
	public static final String SWAGGER_EDIT_SUMMARY = "Modifica un registro existente activo del modelo de datos";	
	public static final String SWAGGER_DELETE_SUMMARY = "Elimina un registro existente del modelo de datos";	
	public static final String SWAGGER_MERGE_SUMMARY = "Crea o modifica un registro existente del modelo de datos";	
	public static final String SWAGGER_ENABLE_SUMMARY = "Habilita/Desahibilita un registro existente del modelo de datos";
	public static final String SWAGGER_NO_DATA_RESPONSE_DESCRIPTION = "No retorna informacion relevante " + LINK_DATA_MODELS 
			+ " => <b>{ \"data\" : null }</b>, solo se debe verificar el codigo http de exito";
}

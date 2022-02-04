package pe.izipay.common.core.types;

import pe.izipay.common.core.interfaces.IType;

public enum DataOriginType implements IType {

	READ_ONLY, 			// 1 => Para datos de solo lectura
	IMPORT, 			// 2 => Para datos que fueron importados de alguna fuente de datos	
	DEFAULT_MANAGMENT, 	// 3 => Para datos que fueron gestionados desde un sistema administrativo
	WEB, 				// 4 => Para datos que fueron gestionados desde una aplicacion web
	ANDROID, 			// 5 => Para datos que fueron gestionados desde una aplicacion android
	IOS, 				// 6 => Para datos que fueron gestionados desde una aplicacion ios
	DESKTOP, 			// 7 => Para datos que fueron gestionados desde una aplicacion de escritorio	
	WEB_SITE, 			// 8 => Para datos que fueron gestionados desde un sitio web cualquiera
	EXTERNAL_APP, 		// 9 => Para datos que fueron gestionados desde una aplicacion externa
	OTHERS, 			// 10 = Para cualquiera dato gestionados del cual se desconoce el origen asignado
	CRON_JOB, 			// 11 => Para datos que fueron gestionados desde una tarea programada que se ejecuta en segundo plano
	LISTENER_JOB, 		// 12 => Para datos que fueron gestionados desde un cola o escuchador en segundo plano
	WORKER_JOB			// 13 => Para datos que fueron gestionados desde un ejecutor constante en segundo plano
}

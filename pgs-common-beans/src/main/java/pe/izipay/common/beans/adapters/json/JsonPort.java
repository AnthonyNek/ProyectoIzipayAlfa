package pe.izipay.common.beans.adapters.json;

import java.util.Map;

public interface JsonPort {
	
	String toJson(Object value);
	<T> T toObject(String value, Class<T> cls);
	<T> T mapToObject(Map<String, String> value, Class<T> cls);
}

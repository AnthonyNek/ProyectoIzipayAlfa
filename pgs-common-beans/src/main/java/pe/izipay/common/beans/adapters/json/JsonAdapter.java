package pe.izipay.common.beans.adapters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import pe.izipay.common.core.exceptions.AppRuntimeException;

import java.util.Map;

@RequiredArgsConstructor
public class JsonAdapter implements JsonPort {

	private final ObjectMapper jsonMapper;

	@Override
	public String toJson(Object value) {
		try {
			return jsonMapper.writeValueAsString(value);
		} catch (JsonProcessingException ex) {
			throw new AppRuntimeException(ex);
		}
	}

	@Override
	public <T> T toObject(String value, Class<T> cls) {
		try {
			return jsonMapper.readValue(value, cls);
		} catch (JsonProcessingException ex) {
			throw new AppRuntimeException(ex);
		}
	}

	@Override
	public <T> T mapToObject(Map<String, String> value, Class<T> cls) {
		return jsonMapper.convertValue(value, cls);
	}

}

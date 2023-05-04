package es.tresdigits.worklogminer.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class JsonUtil {

	private JsonUtil() {
		throw new IllegalArgumentException("Util class");
	}

	/**
	 * Convierte un objecto generico en json
	 *
	 * @param object objecto que se convertira en json
	 * @return String en formato json del objeto
	 * @throws JsonProcessingException
	 */
	public static String objectToJson(Object object) {
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return json;
	}

	/**
	 * Convierte un string en un objeto java
	 *
	 * @param jsonObject el string en formato json
	 * @param object     una instancia de objeto a la que lo queramos convertir
	 * @return el objeto que queremos.
	 */
	public static <T> T jsonToObject(String jsonObject, Class<T> clazz) {
		T objectFinal = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectFinal = objectMapper.readValue(jsonObject, clazz);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return objectFinal;
	}
}

package ch.ffhs.pa5.escapeconnect.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseSettingsJSON {
	public static Map<String, String> parseJSON(String json) {
		Map<String, String> result = new HashMap<String, String>();
		ObjectMapper objectMapper = new ObjectMapper();
		if(json==null) return result;
		try {
			JsonNode root = objectMapper.readTree(json);

			Iterator<Map.Entry<String, JsonNode>> firstlevels = root.fields();
			while (firstlevels.hasNext()) {
				Map.Entry<String, JsonNode> element = firstlevels.next();
				JsonNode val = element.getValue();

				if (val.isValueNode()) {
					result.put(element.getKey(), val.asText());
				}

				if (val.isObject()) {

					Iterator<Map.Entry<String, JsonNode>> secondlevels = val.fields();
					while (secondlevels.hasNext()) {
						Map.Entry<String, JsonNode> element2 = secondlevels.next();

						if (element.getValue().isValueNode()) {
							String name = String.join("/", element.getKey(), element2.getKey());
							result.put(name, element.getValue().asText());
						}

					}
				}
			}
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());;
		}

		return result;
	}
}

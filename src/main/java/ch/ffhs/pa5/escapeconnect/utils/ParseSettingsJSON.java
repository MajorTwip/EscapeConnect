/* Code has been formated */
package ch.ffhs.pa5.escapeconnect.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/** 
 * ParseSettingsJSON allows to parse a JSON File.
 * 
 * @author Yvo von Kaenel
 * 
 */

public class ParseSettingsJSON {
	
   /**  
	* 
	* parseJSON() parses a JSON and generates a Hashmap with the same data. 
    *  
    * @param json as a String	 
    * @return parsed JSON as a hashmap
    *
    */ 
	
  public static Map<String, String> parseJSON(String json) {
    Map<String, String> result = new HashMap<String, String>();
    ObjectMapper objectMapper = new ObjectMapper();
    if (json == null) return result;
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
            if (element2.getValue().isValueNode()) {
              String name = String.join("/", element.getKey(), element2.getKey());
              result.put(name, element2.getValue().asText());
            }
          }
        }
      }
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
      ;
    }

    return result;
  }

  /**  
   * 
   * prepareJSON() creates a JSON for the settings. Used by setSetting(). 
   *  
   * @param settings Hashmap of settings	 
   * @return JSON
   *
   */ 
  
  public static String prepareJSON(Map<String, String> settings) {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode root = objectMapper.createObjectNode();
    for (String key : settings.keySet()) {
      String[] parts = key.split("/");
      if (parts.length == 1) {
        root.put(parts[0], settings.get(key));
      }
      if (parts.length == 2) {
        if (!root.has(parts[0])) {
          root.putObject(parts[0]).put(parts[1], settings.get(key));
        } else {
          ObjectNode secondlevel = (ObjectNode) root.get(parts[0]);
          secondlevel.put(parts[1], settings.get(key));
        }
      }
    }
    return root.toPrettyString();
  }
}

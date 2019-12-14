package ch.ffhs.pa5.escapeconnect.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TestSettingJSON {
	
	@Test
	public void testParseJSON() {
		String tester = "{\n" + 
				"  \"wifi\" : {\n" + 
				"    \"pass\" : \"TestPASS\",\n" + 
				"    \"ssid\" : \"TestSSID\"\n" + 
				"  },\n" + 
				"  \"name\" : \"Name\"\n" + 
				"}";
		Map<String,String> parsed= ParseSettingsJSON.parseJSON(tester);
		
		System.out.println(parsed);
		
		assertEquals("Name", parsed.get("name"));
		assertEquals("TestSSID", parsed.get("wifi/ssid"));
		assertEquals("TestPASS", parsed.get("wifi/pass"));

	}
	
	@Test
	public void testPrepareJson() {
		Map<String,String> settings = new HashMap<>();
		settings.put("name", "Name");
		settings.put("wifi/ssid", "TestSSID");
		settings.put("wifi/pass", "TestPASS");
		
		String wanted = "{\n" + 
				"  \"wifi\" : {\n" + 
				"    \"wifi\" : \"TestPASS\",\n" + 
				"    \"ssid\" : \"TestSSID\"\n" + 
				"  },\n" + 
				"  \"name\" : \"Name\"\n" + 
				"}";
		
		System.out.println(ParseSettingsJSON.prepareJSON(settings));
		assertEquals(wanted.replaceAll("\\s", ""), ParseSettingsJSON.prepareJSON(settings).replaceAll("\\s", ""));
	}
}

package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.SettingApiService;
import ch.ffhs.pa5.escapeconnect.bean.SettingMod;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;

public class SettingAPIimplement implements SettingApiService {

	@Override
	public Response getSettingsByDeviceId(Integer deviceId, SecurityContext securityContext) {
		
		
		
		//Bullshitimplementation nur zum testen ob die MQTTfunktion klappt
		MQTTconnector mqtt = new MQTTconnector("tcp://mqtt.comstock.ch", null, null);
		LinkedList<String> topics = new LinkedList<>();
		topics.add("csh/84f3eb7b2d65/generator/state");
		topics.add("shellies/shellyswitch25-692F79/relay/0");
		Map<String,String> resp = mqtt.getMessages(topics, 1000);
		return Response.status(200).entity(resp).build();
	}

	@Override
	public Response setSetting(List<SettingMod> body, Integer deviceId, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}

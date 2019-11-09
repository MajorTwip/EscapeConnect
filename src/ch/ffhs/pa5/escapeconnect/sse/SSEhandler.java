package ch.ffhs.pa5.escapeconnect.sse;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTmessageHandler;

@Path("/")
public class SSEhandler{

	
	@GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
	public EventOutput  getSSE() throws MqttException {
		final EventOutput eventOutput = new EventOutput();
        
		MQTTconnector con = new MQTTconnector("tcp://mqtt.comstock.ch:1883", null, null);
		con.subscribe(new MQTTmessageHandler() {
			
			@Override
			public boolean onMessage(String topic, String msg) {
				final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                eventBuilder.name(topic);
                eventBuilder.data(msg);
                final OutboundEvent event = eventBuilder.build();
                try {
					eventOutput.write(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
		}, "#");
		
        return eventOutput;	}
}

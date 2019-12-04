package ch.ffhs.pa5.escapeconnect.mqtt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTconnector implements MqttCallback {
	private String url;
	private String name;
	private String pass;
	private MQTTmessageHandler callback;
	
	private MemoryPersistence persistence;
	
	MqttClient client;

	public MQTTconnector(String url, String name, String pass) {
		this.url = url;
		this.name = name;
		this.pass = pass;
		
		this.persistence=new MemoryPersistence();
	}
	
	private void connect() throws MqttException {
		client = new MqttClient(url, "EscapeConnect",persistence);
		MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setWill("EscapeConnect/client", "disconnect".getBytes(), 0, false);
        if(this.name!=null) {
        	connOpts.setUserName(this.name);
        }
        if(this.pass!=null) {
        	connOpts.setPassword(this.pass.toCharArray());
        }
        System.out.println("Connecting to broker: "+url + "  User: " + connOpts.getUserName());
        client.connect(connOpts);
        MqttMessage hello = new MqttMessage();
        hello.setPayload("connect".getBytes());
        client.publish("EscapeConnect/client", hello);
	}
	
	public void subscribe(MQTTmessageHandler callback, String topic) throws MqttException {
		this.callback=callback;
		if(client==null||!client.isConnected()) {
			connect();
		}
		if(client.isConnected()) {
			client.setCallback(this);
			System.out.println("Subscribe to " + topic);
			client.subscribe(topic);
		}
		
	}
	
	public void publish(String topic, MqttMessage msg) {
		try {
			connect();
			this.client.publish(topic, msg);
			while(this.client.getPendingDeliveryTokens().length>0) {
				Thread.sleep(50);
			}
		}catch(MqttException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void close() {
		try {
			this.client.close(true);
			System.out.println("close connection to MQTT");
		}catch (MqttException e){
			//Schliessen einer geschlossenen Verbindung, keine Reaktion nötig
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("got msg, topic: " + topic + " Msg: " + String.valueOf(message.getPayload()));
		if(this.callback!=null) {
			callback.onMessage(topic,String.valueOf(message));
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
	};
	
	volatile boolean completed=false;
	public Map<String,String> getMessages(List<String> topics, int timeout) throws WebApplicationException{
		return getMessages(topics, timeout,false,false);
	}
	
	public Map<String,String> getMessages(List<String> topics, int timeout, boolean wildcard, boolean single) throws WebApplicationException{
		Map<String,String> result = new HashMap<>();
		List<String> topicsOpen = new LinkedList<String>(topics);
		try {
			this.connect();
			for(String topic:topics) {
				client.subscribe(topic, new IMqttMessageListener() {
					@Override
					public void messageArrived(String msgtopic, MqttMessage message) throws Exception {
						if(wildcard) {
							result.put(msgtopic, String.valueOf(message));
							completed=single;
						}else {
							if(topics.contains(msgtopic)){
								topicsOpen.remove(msgtopic);
								//client.unsubscribe(msgtopic);
								result.put(msgtopic, String.valueOf(message));
								if(topicsOpen.size()==0) {
									completed=true;
								}
							}
						}
					}
					
				});
			}
			for(int i=timeout;i>0;i--) {
				Thread.sleep(1);
				if(completed)i=0;
			}
			this.close();
		} catch (MqttSecurityException e) {
			e.printStackTrace();
			throw new WebApplicationException("MQTT Login error");
		} catch (MqttException e) {
			e.printStackTrace();
			throw new WebApplicationException("MQTT error: " + e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}

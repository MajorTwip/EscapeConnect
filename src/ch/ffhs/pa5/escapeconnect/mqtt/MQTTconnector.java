package ch.ffhs.pa5.escapeconnect.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
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
        if(this.name!=null) {
        	connOpts.setUserName(this.name);
        }
        if(this.pass!=null) {
        	connOpts.setPassword(this.pass.toCharArray());
        }
        System.out.println("Connecting to broker: "+url);
        client.connect(connOpts);
	}
	
	public void subscribe(MQTTmessageHandler callback, String topic) throws MqttException {
		this.callback=callback;
		if(client==null||!client.isConnected()) {
			connect();
		}
		if(client.isConnected()) {
			client.setCallback(this);
			client.subscribe(topic);
		}
		
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		if(this.callback!=null) {
			callback.onMessage(topic,message.getPayload().toString());
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
	};
	
	
}

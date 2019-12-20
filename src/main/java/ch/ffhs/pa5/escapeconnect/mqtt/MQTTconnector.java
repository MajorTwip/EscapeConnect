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

/**
 * 
 * @author Yvo von Känel
 * Provides interactions with an MQTT-Broker
 *
 */
public class MQTTconnector implements MqttCallback {
	private String url;
	private String name;
	private String pass;
	private MQTTmessageHandler callback;
	
	private MemoryPersistence persistence;
	
	MqttClient client;

	/**
	 * If used, use {@link #config(String, String, String)} before connecting
	 */
	public MQTTconnector() {}
	
	/**
	 * Creates and configures an Instance ready to interact with an MQTT-Broker
	 * @param url URL of the Broker in the form tcp://url:port
	 * @param name Username to login at the Broker or null
	 * @param pass Password for the Broker or null
	 */
	public MQTTconnector(String url, String name, String pass) {
		this.url = url;
		this.name = name;
		this.pass = pass;
		
		this.persistence=new MemoryPersistence();
	}
	
	/**
	 * (Re)configures an Instance ready to interact with an MQTT-Broker
	 * @param url URL of the Broker in the form tcp://url:port
	 * @param name Username to login at the Broker or null
	 * @param pass Password for the Broker or null
	 */
	public void config(String url, String name, String pass) {
		this.url = url;
		this.name = name;
		this.pass = pass;
		
		this.persistence=new MemoryPersistence();
	}
	
	/**
	 * Connects to the configured Broker
	 * Called by public methods
	 * @throws MqttException see @link https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/MqttException.html
	 */
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
	
	/**
	 * Connects to a Broker and subscribes to a topic
	 * @param callback	Called when a message arrives
	 * @param topic	Topic to subscribe, explicit or wildcard
	 * @throws MqttException see @link https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/MqttException.html
	 */
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
	
	/**
	 * Sends a Message to a given Topic
	 * @param topic Topic
	 * @param msg see @link https://www.eclipse.org/paho/files/javadoc/org/eclipse/paho/client/mqttv3/MqttMessage.html
	 */
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
	
	/**
	 * closes eventual connections
	 * public because needed after async subscriptions {@link #subscribe(MQTTmessageHandler, String)}
	 */
	public void close() {
		try {
			this.client.close(true);
			System.out.println("close connection to MQTT");
		}catch (MqttException | NullPointerException e){
			//Schliessen einer geschlossenen Verbindung, keine Reaktion nötig
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
	}

	
	/**
	 * calls callback given by {@link #subscribe(MQTTmessageHandler, String)}
	 */
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
	/**
	 * Subscribes to a list of Topics and waits for at least one response per given topic
	 * Returns when every topic returned at least one response or after timeout
	 * @param topics List of Topics to subscribe
	 * @param timeout Maximal time to wait
	 * @return Map with topics as key (same as param topics, reordered) and the response
	 * @throws WebApplicationException Wraps MqttExeptions
	 */
	public Map<String,String> getMessages(List<String> topics, int timeout) throws WebApplicationException{
		return getMessages(topics, timeout,false,false);
	}
	
	/**
	 * Subscribes to a list of Topics and waits for at least one response per given topic
	 * Returns when every topic returned at least one response or after timeout
	 * @param wildcard if wildcards are used (then multiple responses per topic can occure). Will wait till timeout
	 * @param single if wildcards are used awaits only first response
	 * @param topics List of Topics to subscribe
	 * @param timeout Maximal time to wait
	 * @return Map with topics as key (same as param topics, reordered) and the response
	 * @throws WebApplicationException Wraps MqttExeptions
	 */
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

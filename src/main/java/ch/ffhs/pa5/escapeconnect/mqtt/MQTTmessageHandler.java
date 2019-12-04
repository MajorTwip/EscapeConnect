package ch.ffhs.pa5.escapeconnect.mqtt;

public interface MQTTmessageHandler {
	public boolean onMessage(String topic, String msg);
}

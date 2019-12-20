package ch.ffhs.pa5.escapeconnect.mqtt;

/**
 * Interface for a Messagelistenener
 * @author Yvo von Känel
 * Used for MQTTconnector.subscribe(...)
 */
public interface MQTTmessageHandler {
	/**
	 * Called when a message from Broke is received
	 * @param topic Message's topic
	 * @param msg Message
	 * @return handling finished (false = propagate further, future)
	 */
	public boolean onMessage(String topic, String msg);
}

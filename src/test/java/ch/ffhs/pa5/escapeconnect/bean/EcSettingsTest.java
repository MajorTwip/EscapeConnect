package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Test;

public class EcSettingsTest {

	private EcSettings createTestSubject() {
		return new EcSettings("", "", "", "");
	}

	@MethodRef(name = "getPassword", signature = "()QString;")
	@Test
	public void testGetPassword() throws Exception {
		EcSettings testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPassword();
	}

	@MethodRef(name = "setPassword", signature = "(QString;)V")
	@Test
	public void testSetPassword() throws Exception {
		EcSettings testSubject;
		String password = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setPassword(password);
	}

	@MethodRef(name = "getMqttUrl", signature = "()QString;")
	@Test
	public void testGetMqttUrl() throws Exception {
		EcSettings testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMqttUrl();
	}

	@MethodRef(name = "setMqttUrl", signature = "(QString;)V")
	@Test
	public void testSetMqttUrl() throws Exception {
		EcSettings testSubject;
		String mqttUrl = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMqttUrl(mqttUrl);
	}

	@MethodRef(name = "getMqttName", signature = "()QString;")
	@Test
	public void testGetMqttName() throws Exception {
		EcSettings testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMqttName();
	}

	@MethodRef(name = "setMqttName", signature = "(QString;)V")
	@Test
	public void testSetMqttName() throws Exception {
		EcSettings testSubject;
		String mqttName = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMqttName(mqttName);
	}

	@MethodRef(name = "getMqttPass", signature = "()QString;")
	@Test
	public void testGetMqttPass() throws Exception {
		EcSettings testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMqttPass();
	}

	@MethodRef(name = "setMqttPass", signature = "(QString;)V")
	@Test
	public void testSetMqttPass() throws Exception {
		EcSettings testSubject;
		String mqttPass = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMqttPass(mqttPass);
	}
}
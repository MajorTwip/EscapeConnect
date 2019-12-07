package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Assert;
import org.junit.Test;

public class SetupTest {

	private Setup createTestSubject() {
		return new Setup();
	}

	@MethodRef(name = "mqtturl", signature = "(QString;)QSetup;")
	@Test
	public void testMqtturl() throws Exception {
		Setup testSubject;
		String mqtturl = "";
		Setup result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.mqtturl(mqtturl);
	}

	@MethodRef(name = "getMqtturl", signature = "()QString;")
	@Test
	public void testGetMqtturl() throws Exception {
		Setup testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMqtturl();
	}

	@MethodRef(name = "setMqtturl", signature = "(QString;)V")
	@Test
	public void testSetMqtturl() throws Exception {
		Setup testSubject;
		String mqtturl = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMqtturl(mqtturl);
	}

	@MethodRef(name = "mqttuser", signature = "(QString;)QSetup;")
	@Test
	public void testMqttuser() throws Exception {
		Setup testSubject;
		String mqttuser = "";
		Setup result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.mqttuser(mqttuser);
	}

	@MethodRef(name = "getMqttuser", signature = "()QString;")
	@Test
	public void testGetMqttuser() throws Exception {
		Setup testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMqttuser();
	}

	@MethodRef(name = "setMqttuser", signature = "(QString;)V")
	@Test
	public void testSetMqttuser() throws Exception {
		Setup testSubject;
		String mqttuser = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMqttuser(mqttuser);
	}

	@MethodRef(name = "mqttpass", signature = "(QString;)QSetup;")
	@Test
	public void testMqttpass() throws Exception {
		Setup testSubject;
		String mqttpass = "";
		Setup result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.mqttpass(mqttpass);
	}

	@MethodRef(name = "getMqttpass", signature = "()QString;")
	@Test
	public void testGetMqttpass() throws Exception {
		Setup testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMqttpass();
	}

	@MethodRef(name = "setMqttpass", signature = "(QString;)V")
	@Test
	public void testSetMqttpass() throws Exception {
		Setup testSubject;
		String mqttpass = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMqttpass(mqttpass);
	}

	@MethodRef(name = "adminpass", signature = "(QString;)QSetup;")
	@Test
	public void testAdminpass() throws Exception {
		Setup testSubject;
		String adminpass = "";
		Setup result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.adminpass(adminpass);
	}

	@MethodRef(name = "getAdminpass", signature = "()QString;")
	@Test
	public void testGetAdminpass() throws Exception {
		Setup testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getAdminpass();
	}

	@MethodRef(name = "setAdminpass", signature = "(QString;)V")
	@Test
	public void testSetAdminpass() throws Exception {
		Setup testSubject;
		String adminpass = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setAdminpass(adminpass);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		Setup testSubject;
		Object o = null;
		boolean result;

		// test 1
		testSubject = createTestSubject();
		o = null;
		result = testSubject.equals(o);
		Assert.assertEquals(false, result);
	}

	@MethodRef(name = "hashCode", signature = "()I")
	@Test
	public void testHashCode() throws Exception {
		Setup testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		Setup testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}
}
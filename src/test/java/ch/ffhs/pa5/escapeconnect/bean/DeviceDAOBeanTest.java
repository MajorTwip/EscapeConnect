package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Test;

public class DeviceDAOBeanTest {

	private DeviceDAOBean createTestSubject() {
		return new DeviceDAOBean();
	}

	@MethodRef(name = "getName", signature = "()QString;")
	@Test
	public void testGetName() throws Exception {
		DeviceDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getName();
	}

	@MethodRef(name = "setName", signature = "(QString;)V")
	@Test
	public void testSetName() throws Exception {
		DeviceDAOBean testSubject;
		String name = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setName(name);
	}

	@MethodRef(name = "getMac", signature = "()QString;")
	@Test
	public void testGetMac() throws Exception {
		DeviceDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMac();
	}

	@MethodRef(name = "setMac", signature = "(QString;)V")
	@Test
	public void testSetMac() throws Exception {
		DeviceDAOBean testSubject;
		String mac = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMac(mac);
	}

	@MethodRef(name = "issupportsOTA", signature = "()Z")
	@Test
	public void testIssupportsOTA() throws Exception {
		DeviceDAOBean testSubject;
		boolean result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.issupportsOTA();
	}

	@MethodRef(name = "setsupportsOTA", signature = "(Z)V")
	@Test
	public void testSetsupportsOTA() throws Exception {
		DeviceDAOBean testSubject;
		boolean allowOTA = false;

		// default test
		testSubject = createTestSubject();
		testSubject.setsupportsOTA(allowOTA);
	}

	@MethodRef(name = "getBasetopic", signature = "()QString;")
	@Test
	public void testGetBasetopic() throws Exception {
		DeviceDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getBasetopic();
	}

	@MethodRef(name = "setBasetopic", signature = "(QString;)V")
	@Test
	public void testSetBasetopic() throws Exception {
		DeviceDAOBean testSubject;
		String basetopic = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setBasetopic(basetopic);
	}

	@MethodRef(name = "getDeviceid", signature = "()QString;")
	@Test
	public void testGetDeviceid() throws Exception {
		DeviceDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getDeviceid();
	}

	@MethodRef(name = "setDeviceid", signature = "(QString;)V")
	@Test
	public void testSetDeviceid() throws Exception {
		DeviceDAOBean testSubject;
		String deviceid = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setDeviceid(deviceid);
	}

	@MethodRef(name = "getFirmwareid", signature = "()I")
	@Test
	public void testGetFirmwareid() throws Exception {
		DeviceDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getFirmwareid();
	}

	@MethodRef(name = "setFirmwareid", signature = "(I)V")
	@Test
	public void testSetFirmwareid() throws Exception {
		DeviceDAOBean testSubject;
		int firmwareid = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setFirmwareid(firmwareid);
	}
}
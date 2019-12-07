package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Assert;
import org.junit.Test;

public class UpdateDeviceBodyTest {

	private UpdateDeviceBody createTestSubject() {
		return new UpdateDeviceBody();
	}

	@MethodRef(name = "firmware", signature = "([B)QUpdateDeviceBody;")
	@Test
	public void testFirmware() throws Exception {
		UpdateDeviceBody testSubject;
		byte[] firmware = new byte[] { ' ' };
		UpdateDeviceBody result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.firmware(firmware);
	}

	@MethodRef(name = "getFirmware", signature = "()[B")
	@Test
	public void testGetFirmware() throws Exception {
		UpdateDeviceBody testSubject;
		byte[] result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getFirmware();
	}

	@MethodRef(name = "setFirmware", signature = "([B)V")
	@Test
	public void testSetFirmware() throws Exception {
		UpdateDeviceBody testSubject;
		byte[] firmware = new byte[] { ' ' };

		// default test
		testSubject = createTestSubject();
		testSubject.setFirmware(firmware);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		UpdateDeviceBody testSubject;
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
		UpdateDeviceBody testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		UpdateDeviceBody testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}
}
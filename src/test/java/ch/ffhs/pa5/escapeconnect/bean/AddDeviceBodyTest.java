package ch.ffhs.pa5.escapeconnect.bean;

import javax.annotation.Generated;

import org.junit.Assert;
import org.junit.Test;

@Generated(value = "org.junit-tools-1.1.0")
public class AddDeviceBodyTest {

	private AddDeviceBody createTestSubject() {
		return new AddDeviceBody();
	}

	@MethodRef(name = "name", signature = "(QString;)QAddDeviceBody;")
	@Test
	public void testName() throws Exception {
		AddDeviceBody testSubject;
		String name = "";
		AddDeviceBody result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.name(name);
	}

	@MethodRef(name = "getName", signature = "()QString;")
	@Test
	public void testGetName() throws Exception {
		AddDeviceBody testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getName();
	}

	@MethodRef(name = "setName", signature = "(QString;)V")
	@Test
	public void testSetName() throws Exception {
		AddDeviceBody testSubject;
		String name = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setName(name);
	}

	@MethodRef(name = "file", signature = "([B)QAddDeviceBody;")
	@Test
	public void testFile() throws Exception {
		AddDeviceBody testSubject;
		byte[] file = new byte[] { ' ' };
		AddDeviceBody result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.file(file);
	}

	@MethodRef(name = "getFile", signature = "()[B")
	@Test
	public void testGetFile() throws Exception {
		AddDeviceBody testSubject;
		byte[] result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getFile();
	}

	@MethodRef(name = "setFile", signature = "([B)V")
	@Test
	public void testSetFile() throws Exception {
		AddDeviceBody testSubject;
		byte[] file = new byte[] { ' ' };

		// default test
		testSubject = createTestSubject();
		testSubject.setFile(file);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		AddDeviceBody testSubject;
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
		AddDeviceBody testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		AddDeviceBody testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}
}
package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Test;

public class PanelDAOBeanTest {

	private PanelDAOBean createTestSubject() {
		return new PanelDAOBean();
	}

	@MethodRef(name = "getId", signature = "()I")
	@Test
	public void testGetId() throws Exception {
		PanelDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(I)V")
	@Test
	public void testSetId() throws Exception {
		PanelDAOBean testSubject;
		int id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "getName", signature = "()QString;")
	@Test
	public void testGetName() throws Exception {
		PanelDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getName();
	}

	@MethodRef(name = "setName", signature = "(QString;)V")
	@Test
	public void testSetName() throws Exception {
		PanelDAOBean testSubject;
		String name = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setName(name);
	}

	@MethodRef(name = "getDevice_mac", signature = "()QString;")
	@Test
	public void testGetDevice_mac() throws Exception {
		PanelDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getDevice_mac();
	}

	@MethodRef(name = "setDevice_mac", signature = "(QString;)V")
	@Test
	public void testSetDevice_mac() throws Exception {
		PanelDAOBean testSubject;
		String device_mac = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setDevice_mac(device_mac);
	}
}
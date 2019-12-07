package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Test;

public class SettingDAOBeanTest {

	private SettingDAOBean createTestSubject() {
		return new SettingDAOBean();
	}

	@MethodRef(name = "getId", signature = "()I")
	@Test
	public void testGetId() throws Exception {
		SettingDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(I)V")
	@Test
	public void testSetId() throws Exception {
		SettingDAOBean testSubject;
		int id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "getDevice_mac", signature = "()QString;")
	@Test
	public void testGetDevice_mac() throws Exception {
		SettingDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getDevice_mac();
	}

	@MethodRef(name = "setDevice_mac", signature = "(QString;)V")
	@Test
	public void testSetDevice_mac() throws Exception {
		SettingDAOBean testSubject;
		String device_mac = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setDevice_mac(device_mac);
	}

	@MethodRef(name = "getPanel_id", signature = "()I")
	@Test
	public void testGetPanel_id() throws Exception {
		SettingDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPanel_id();
	}

	@MethodRef(name = "setPanel_id", signature = "(I)V")
	@Test
	public void testSetPanel_id() throws Exception {
		SettingDAOBean testSubject;
		int panel_id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setPanel_id(panel_id);
	}

	@MethodRef(name = "getLabel", signature = "()QString;")
	@Test
	public void testGetLabel() throws Exception {
		SettingDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getLabel();
	}

	@MethodRef(name = "setLabel", signature = "(QString;)V")
	@Test
	public void testSetLabel() throws Exception {
		SettingDAOBean testSubject;
		String label = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setLabel(label);
	}

	@MethodRef(name = "getValue", signature = "()QString;")
	@Test
	public void testGetValue() throws Exception {
		SettingDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getValue();
	}

	@MethodRef(name = "setValue", signature = "(QString;)V")
	@Test
	public void testSetValue() throws Exception {
		SettingDAOBean testSubject;
		String value = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setValue(value);
	}

	@MethodRef(name = "getName", signature = "()QString;")
	@Test
	public void testGetName() throws Exception {
		SettingDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getName();
	}

	@MethodRef(name = "setName", signature = "(QString;)V")
	@Test
	public void testSetName() throws Exception {
		SettingDAOBean testSubject;
		String name = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setName(name);
	}

	@MethodRef(name = "getType", signature = "()QString;")
	@Test
	public void testGetType() throws Exception {
		SettingDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getType();
	}

	@MethodRef(name = "setType", signature = "(QString;)V")
	@Test
	public void testSetType() throws Exception {
		SettingDAOBean testSubject;
		String type = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setType(type);
	}

	@MethodRef(name = "getMin", signature = "()I")
	@Test
	public void testGetMin() throws Exception {
		SettingDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMin();
	}

	@MethodRef(name = "setMin", signature = "(I)V")
	@Test
	public void testSetMin() throws Exception {
		SettingDAOBean testSubject;
		int min = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setMin(min);
	}

	@MethodRef(name = "getMax", signature = "()I")
	@Test
	public void testGetMax() throws Exception {
		SettingDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getMax();
	}

	@MethodRef(name = "setMax", signature = "(I)V")
	@Test
	public void testSetMax() throws Exception {
		SettingDAOBean testSubject;
		int max = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setMax(max);
	}
}
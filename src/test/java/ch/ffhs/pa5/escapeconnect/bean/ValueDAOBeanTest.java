package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Test;

public class ValueDAOBeanTest {

	private ValueDAOBean createTestSubject() {
		return new ValueDAOBean();
	}

	@MethodRef(name = "getId", signature = "()I")
	@Test
	public void testGetId() throws Exception {
		ValueDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(I)V")
	@Test
	public void testSetId() throws Exception {
		ValueDAOBean testSubject;
		int id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "getPanel_id", signature = "()I")
	@Test
	public void testGetPanel_id() throws Exception {
		ValueDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPanel_id();
	}

	@MethodRef(name = "setPanel_id", signature = "(I)V")
	@Test
	public void testSetPanel_id() throws Exception {
		ValueDAOBean testSubject;
		int panel_id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setPanel_id(panel_id);
	}

	@MethodRef(name = "getLabel", signature = "()QString;")
	@Test
	public void testGetLabel() throws Exception {
		ValueDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getLabel();
	}

	@MethodRef(name = "setLabel", signature = "(QString;)V")
	@Test
	public void testSetLabel() throws Exception {
		ValueDAOBean testSubject;
		String label = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setLabel(label);
	}

	@MethodRef(name = "getSubtopic", signature = "()QString;")
	@Test
	public void testGetSubtopic() throws Exception {
		ValueDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getSubtopic();
	}

	@MethodRef(name = "setSubtopic", signature = "(QString;)V")
	@Test
	public void testSetSubtopic() throws Exception {
		ValueDAOBean testSubject;
		String topic = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setSubtopic(topic);
	}

	@MethodRef(name = "getType", signature = "()QString;")
	@Test
	public void testGetType() throws Exception {
		ValueDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getType();
	}

	@MethodRef(name = "setType", signature = "(QString;)V")
	@Test
	public void testSetType() throws Exception {
		ValueDAOBean testSubject;
		String type = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setType(type);
	}
}
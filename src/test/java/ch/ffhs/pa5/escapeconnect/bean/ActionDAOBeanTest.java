package ch.ffhs.pa5.escapeconnect.bean;

import javax.annotation.Generated;

import org.junit.Test;

@Generated(value = "org.junit-tools-1.1.0")
public class ActionDAOBeanTest {

	private ActionDAOBean createTestSubject() {
		return new ActionDAOBean();
	}

	@MethodRef(name = "getId", signature = "()I")
	@Test
	public void testGetId() throws Exception {
		ActionDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(I)V")
	@Test
	public void testSetId() throws Exception {
		ActionDAOBean testSubject;
		int id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "getPanel_id", signature = "()I")
	@Test
	public void testGetPanel_id() throws Exception {
		ActionDAOBean testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPanel_id();
	}

	@MethodRef(name = "setPanel_id", signature = "(I)V")
	@Test
	public void testSetPanel_id() throws Exception {
		ActionDAOBean testSubject;
		int panel_id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setPanel_id(panel_id);
	}

	@MethodRef(name = "getLabel", signature = "()QString;")
	@Test
	public void testGetLabel() throws Exception {
		ActionDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getLabel();
	}

	@MethodRef(name = "setLabel", signature = "(QString;)V")
	@Test
	public void testSetLabel() throws Exception {
		ActionDAOBean testSubject;
		String label = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setLabel(label);
	}

	@MethodRef(name = "getBasetopic", signature = "()QString;")
	@Test
	public void testGetBasetopic() throws Exception {
		ActionDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getBasetopic();
	}

	@MethodRef(name = "setBasetopic", signature = "(QString;)V")
	@Test
	public void testSetBasetopic() throws Exception {
		ActionDAOBean testSubject;
		String basetopic = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setBasetopic(basetopic);
	}

	@MethodRef(name = "getDeviceid", signature = "()QString;")
	@Test
	public void testGetDeviceid() throws Exception {
		ActionDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getDeviceid();
	}

	@MethodRef(name = "setDeviceid", signature = "(QString;)V")
	@Test
	public void testSetDeviceid() throws Exception {
		ActionDAOBean testSubject;
		String deviceid = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setDeviceid(deviceid);
	}

	@MethodRef(name = "getSubtopic", signature = "()QString;")
	@Test
	public void testGetSubtopic() throws Exception {
		ActionDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getSubtopic();
	}

	@MethodRef(name = "setSubtopic", signature = "(QString;)V")
	@Test
	public void testSetSubtopic() throws Exception {
		ActionDAOBean testSubject;
		String topic = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setSubtopic(topic);
	}

	@MethodRef(name = "getPayload", signature = "()QString;")
	@Test
	public void testGetPayload() throws Exception {
		ActionDAOBean testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPayload();
	}

	@MethodRef(name = "setPayload", signature = "(QString;)V")
	@Test
	public void testSetPayload() throws Exception {
		ActionDAOBean testSubject;
		String payload = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setPayload(payload);
	}
}
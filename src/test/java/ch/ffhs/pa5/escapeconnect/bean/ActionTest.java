package ch.ffhs.pa5.escapeconnect.bean;

import javax.annotation.Generated;

import org.junit.Assert;
import org.junit.Test;

@Generated(value = "org.junit-tools-1.1.0")
public class ActionTest {

	private Action createTestSubject() {
		return new Action();
	}

	@MethodRef(name = "id", signature = "(QInteger;)QAction;")
	@Test
	public void testId() throws Exception {
		Action testSubject;
		Integer id = 0;
		Action result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.id(id);
	}

	@MethodRef(name = "getId", signature = "()QInteger;")
	@Test
	public void testGetId() throws Exception {
		Action testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(QInteger;)V")
	@Test
	public void testSetId() throws Exception {
		Action testSubject;
		Integer id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "label", signature = "(QString;)QAction;")
	@Test
	public void testLabel() throws Exception {
		Action testSubject;
		String label = "";
		Action result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.label(label);
	}

	@MethodRef(name = "getLabel", signature = "()QString;")
	@Test
	public void testGetLabel() throws Exception {
		Action testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getLabel();
	}

	@MethodRef(name = "setLabel", signature = "(QString;)V")
	@Test
	public void testSetLabel() throws Exception {
		Action testSubject;
		String label = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setLabel(label);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		Action testSubject;
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
		Action testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		Action testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}

}
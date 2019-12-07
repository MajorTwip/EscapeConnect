package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Assert;
import org.junit.Test;

public class ValueTest {

	private Value createTestSubject() {
		return new Value();
	}

	@MethodRef(name = "id", signature = "(QInteger;)QValue;")
	@Test
	public void testId() throws Exception {
		Value testSubject;
		Integer id = 0;
		Value result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.id(id);
	}

	@MethodRef(name = "getId", signature = "()QInteger;")
	@Test
	public void testGetId() throws Exception {
		Value testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(QInteger;)V")
	@Test
	public void testSetId() throws Exception {
		Value testSubject;
		Integer id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "label", signature = "(QString;)QValue;")
	@Test
	public void testLabel() throws Exception {
		Value testSubject;
		String label = "";
		Value result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.label(label);
	}

	@MethodRef(name = "getLabel", signature = "()QString;")
	@Test
	public void testGetLabel() throws Exception {
		Value testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getLabel();
	}

	@MethodRef(name = "setLabel", signature = "(QString;)V")
	@Test
	public void testSetLabel() throws Exception {
		Value testSubject;
		String label = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setLabel(label);
	}

	@MethodRef(name = "value", signature = "(QString;)QValue;")
	@Test
	public void testValue() throws Exception {
		Value testSubject;
		String value = "";
		Value result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.value(value);
	}

	@MethodRef(name = "getValue", signature = "()QString;")
	@Test
	public void testGetValue() throws Exception {
		Value testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getValue();
	}

	@MethodRef(name = "setValue", signature = "(QString;)V")
	@Test
	public void testSetValue() throws Exception {
		Value testSubject;
		String value = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setValue(value);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		Value testSubject;
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
		Value testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		Value testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}
}
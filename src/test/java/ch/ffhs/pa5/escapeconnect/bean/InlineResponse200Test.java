package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Assert;
import org.junit.Test;

public class InlineResponse200Test {

	private InlineResponse200 createTestSubject() {
		return new InlineResponse200();
	}

	@MethodRef(name = "byteswritten", signature = "(QInteger;)QInlineResponse200;")
	@Test
	public void testByteswritten() throws Exception {
		InlineResponse200 testSubject;
		Integer byteswritten = 0;
		InlineResponse200 result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.byteswritten(byteswritten);
	}

	@MethodRef(name = "getByteswritten", signature = "()QInteger;")
	@Test
	public void testGetByteswritten() throws Exception {
		InlineResponse200 testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getByteswritten();
	}

	@MethodRef(name = "setByteswritten", signature = "(QInteger;)V")
	@Test
	public void testSetByteswritten() throws Exception {
		InlineResponse200 testSubject;
		Integer byteswritten = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setByteswritten(byteswritten);
	}

	@MethodRef(name = "bytestotal", signature = "(QInteger;)QInlineResponse200;")
	@Test
	public void testBytestotal() throws Exception {
		InlineResponse200 testSubject;
		Integer bytestotal = 0;
		InlineResponse200 result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.bytestotal(bytestotal);
	}

	@MethodRef(name = "getBytestotal", signature = "()QInteger;")
	@Test
	public void testGetBytestotal() throws Exception {
		InlineResponse200 testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getBytestotal();
	}

	@MethodRef(name = "setBytestotal", signature = "(QInteger;)V")
	@Test
	public void testSetBytestotal() throws Exception {
		InlineResponse200 testSubject;
		Integer bytestotal = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setBytestotal(bytestotal);
	}

	@MethodRef(name = "status", signature = "(QInteger;)QInlineResponse200;")
	@Test
	public void testStatus() throws Exception {
		InlineResponse200 testSubject;
		Integer status = 0;
		InlineResponse200 result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.status(status);
	}

	@MethodRef(name = "getStatus", signature = "()QInteger;")
	@Test
	public void testGetStatus() throws Exception {
		InlineResponse200 testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getStatus();
	}

	@MethodRef(name = "setStatus", signature = "(QInteger;)V")
	@Test
	public void testSetStatus() throws Exception {
		InlineResponse200 testSubject;
		Integer status = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setStatus(status);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		InlineResponse200 testSubject;
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
		InlineResponse200 testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		InlineResponse200 testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}
}
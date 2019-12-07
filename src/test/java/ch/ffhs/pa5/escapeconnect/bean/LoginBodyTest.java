package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Assert;
import org.junit.Test;

public class LoginBodyTest {

	private LoginBody createTestSubject() {
		return new LoginBody();
	}

	@MethodRef(name = "passhash", signature = "(QString;)QLoginBody;")
	@Test
	public void testPasshash() throws Exception {
		LoginBody testSubject;
		String passhash = "";
		LoginBody result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.passhash(passhash);
	}

	@MethodRef(name = "getPasshash", signature = "()QString;")
	@Test
	public void testGetPasshash() throws Exception {
		LoginBody testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPasshash();
	}

	@MethodRef(name = "setPasshash", signature = "(QString;)V")
	@Test
	public void testSetPasshash() throws Exception {
		LoginBody testSubject;
		String passhash = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setPasshash(passhash);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		LoginBody testSubject;
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
		LoginBody testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		LoginBody testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}
}
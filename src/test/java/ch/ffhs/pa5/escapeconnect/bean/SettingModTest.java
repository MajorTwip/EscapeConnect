package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Assert;
import org.junit.Test;

public class SettingModTest {

	private SettingMod createTestSubject() {
		return new SettingMod();
	}

	@MethodRef(name = "id", signature = "(QInteger;)QSettingMod;")
	@Test
	public void testId() throws Exception {
		SettingMod testSubject;
		Integer id = 0;
		SettingMod result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.id(id);
	}

	@MethodRef(name = "getId", signature = "()QInteger;")
	@Test
	public void testGetId() throws Exception {
		SettingMod testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(QInteger;)V")
	@Test
	public void testSetId() throws Exception {
		SettingMod testSubject;
		Integer id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "value", signature = "(QString;)QSettingMod;")
	@Test
	public void testValue() throws Exception {
		SettingMod testSubject;
		String value = "";
		SettingMod result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.value(value);
	}

	@MethodRef(name = "getValue", signature = "()QString;")
	@Test
	public void testGetValue() throws Exception {
		SettingMod testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getValue();
	}

	@MethodRef(name = "setValue", signature = "(QString;)V")
	@Test
	public void testSetValue() throws Exception {
		SettingMod testSubject;
		String value = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setValue(value);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		SettingMod testSubject;
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
		SettingMod testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		SettingMod testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}
}
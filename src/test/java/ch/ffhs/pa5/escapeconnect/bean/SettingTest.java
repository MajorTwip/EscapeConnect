package ch.ffhs.pa5.escapeconnect.bean;

import org.junit.Assert;
import org.junit.Test;

import ch.ffhs.pa5.escapeconnect.bean.Setting.TypeEnum;

public class SettingTest {

	private Setting createTestSubject() {
		return new Setting();
	}

	@MethodRef(name = "id", signature = "(QInteger;)QSetting;")
	@Test
	public void testId() throws Exception {
		Setting testSubject;
		Integer id = 0;
		// default test
		testSubject = createTestSubject();
		testSubject.id(id);
	}

	@MethodRef(name = "getId", signature = "()QInteger;")
	@Test
	public void testGetId() throws Exception {
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(QInteger;)V")
	@Test
	public void testSetId() throws Exception {
		Setting testSubject;
		Integer id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "label", signature = "(QString;)QSetting;")
	@Test
	public void testLabel() throws Exception {
		Setting testSubject;
		String label = "";
		// default test
		testSubject = createTestSubject();
		testSubject.label(label);
	}

	@MethodRef(name = "getLabel", signature = "()QString;")
	@Test
	public void testGetLabel() throws Exception {
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.getLabel();
	}

	@MethodRef(name = "setLabel", signature = "(QString;)V")
	@Test
	public void testSetLabel() throws Exception {
		Setting testSubject;
		String label = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setLabel(label);
	}

	@MethodRef(name = "type", signature = "(QTypeEnum;)QSetting;")
	@Test
	public void testType() throws Exception {
		Setting testSubject;
		TypeEnum type = null;
		// default test
		testSubject = createTestSubject();
		testSubject.type(type);
	}

	@MethodRef(name = "getType", signature = "()QTypeEnum;")
	@Test
	public void testGetType() throws Exception {
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.getType();
	}

	@MethodRef(name = "setType", signature = "(QTypeEnum;)V")
	@Test
	public void testSetType() throws Exception {
		Setting testSubject;
		TypeEnum type = null;

		// default test
		testSubject = createTestSubject();
		testSubject.setType(type);
	}

	@MethodRef(name = "value", signature = "(QString;)QSetting;")
	@Test
	public void testValue() throws Exception {
		Setting testSubject;
		String value = "";
		// default test
		testSubject = createTestSubject();
		testSubject.value(value);
	}

	@MethodRef(name = "getValue", signature = "()QString;")
	@Test
	public void testGetValue() throws Exception {
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.getValue();
	}

	@MethodRef(name = "setValue", signature = "(QString;)V")
	@Test
	public void testSetValue() throws Exception {
		Setting testSubject;
		String value = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setValue(value);
	}

	@MethodRef(name = "min", signature = "(QString;)QSetting;")
	@Test
	public void testMin() throws Exception {
		Setting testSubject;
		String min = "";
		// default test
		testSubject = createTestSubject();
		testSubject.min(min);
	}

	@MethodRef(name = "getMin", signature = "()QString;")
	@Test
	public void testGetMin() throws Exception {
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.getMin();
	}

	@MethodRef(name = "setMin", signature = "(QString;)V")
	@Test
	public void testSetMin() throws Exception {
		Setting testSubject;
		String min = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMin(min);
	}

	@MethodRef(name = "max", signature = "(QString;)QSetting;")
	@Test
	public void testMax() throws Exception {
		Setting testSubject;
		String max = "";
		// default test
		testSubject = createTestSubject();
		testSubject.max(max);
	}

	@MethodRef(name = "getMax", signature = "()QString;")
	@Test
	public void testGetMax() throws Exception {
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.getMax();
	}

	@MethodRef(name = "setMax", signature = "(QString;)V")
	@Test
	public void testSetMax() throws Exception {
		Setting testSubject;
		String max = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setMax(max);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		Setting testSubject;
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
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		Setting testSubject;
		// default test
		testSubject = createTestSubject();
		testSubject.toString();
	}
}
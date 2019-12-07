package ch.ffhs.pa5.escapeconnect.bean;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PanelTest {

	private Panel createTestSubject() {
		return new Panel();
	}

	@MethodRef(name = "id", signature = "(QInteger;)QPanel;")
	@Test
	public void testId() throws Exception {
		Panel testSubject;
		Integer id = 0;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.id(id);
	}

	@MethodRef(name = "getId", signature = "()QInteger;")
	@Test
	public void testGetId() throws Exception {
		Panel testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getId();
	}

	@MethodRef(name = "setId", signature = "(QInteger;)V")
	@Test
	public void testSetId() throws Exception {
		Panel testSubject;
		Integer id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setId(id);
	}

	@MethodRef(name = "order", signature = "(QInteger;)QPanel;")
	@Test
	public void testOrder() throws Exception {
		Panel testSubject;
		Integer order = 0;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.order(order);
	}

	@MethodRef(name = "getOrder", signature = "()QInteger;")
	@Test
	public void testGetOrder() throws Exception {
		Panel testSubject;
		Integer result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getOrder();
	}

	@MethodRef(name = "setOrder", signature = "(QInteger;)V")
	@Test
	public void testSetOrder() throws Exception {
		Panel testSubject;
		Integer order = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setOrder(order);
	}

	@MethodRef(name = "title", signature = "(QString;)QPanel;")
	@Test
	public void testTitle() throws Exception {
		Panel testSubject;
		String title = "";
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.title(title);
	}

	@MethodRef(name = "getTitle", signature = "()QString;")
	@Test
	public void testGetTitle() throws Exception {
		Panel testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getTitle();
	}

	@MethodRef(name = "setTitle", signature = "(QString;)V")
	@Test
	public void testSetTitle() throws Exception {
		Panel testSubject;
		String title = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setTitle(title);
	}

	@MethodRef(name = "status", signature = "(QBoolean;)QPanel;")
	@Test
	public void testStatus() throws Exception {
		Panel testSubject;
		Boolean status = null;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.status(status);
	}

	@MethodRef(name = "isisStatus", signature = "()QBoolean;")
	@Test
	public void testIsisStatus() throws Exception {
		Panel testSubject;
		Boolean result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.isisStatus();
	}

	@MethodRef(name = "setStatus", signature = "(QBoolean;)V")
	@Test
	public void testSetStatus() throws Exception {
		Panel testSubject;
		Boolean status = null;

		// default test
		testSubject = createTestSubject();
		testSubject.setStatus(status);
	}

	@MethodRef(name = "settingsenabled", signature = "(QBoolean;)QPanel;")
	@Test
	public void testSettingsenabled() throws Exception {
		Panel testSubject;
		Boolean settingsenabled = null;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.settingsenabled(settingsenabled);
	}

	@MethodRef(name = "isisSettingsenabled", signature = "()QBoolean;")
	@Test
	public void testIsisSettingsenabled() throws Exception {
		Panel testSubject;
		Boolean result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.isisSettingsenabled();
	}

	@MethodRef(name = "setSettingsenabled", signature = "(QBoolean;)V")
	@Test
	public void testSetSettingsenabled() throws Exception {
		Panel testSubject;
		Boolean settingsenabled = null;

		// default test
		testSubject = createTestSubject();
		testSubject.setSettingsenabled(settingsenabled);
	}

	@MethodRef(name = "upgradeenabled", signature = "(QBoolean;)QPanel;")
	@Test
	public void testUpgradeenabled() throws Exception {
		Panel testSubject;
		Boolean upgradeenabled = null;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.upgradeenabled(upgradeenabled);
	}

	@MethodRef(name = "isisUpgradeenabled", signature = "()QBoolean;")
	@Test
	public void testIsisUpgradeenabled() throws Exception {
		Panel testSubject;
		Boolean result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.isisUpgradeenabled();
	}

	@MethodRef(name = "setUpgradeenabled", signature = "(QBoolean;)V")
	@Test
	public void testSetUpgradeenabled() throws Exception {
		Panel testSubject;
		Boolean upgradeenabled = null;

		// default test
		testSubject = createTestSubject();
		testSubject.setUpgradeenabled(upgradeenabled);
	}

	@MethodRef(name = "values", signature = "(QList<QValue;>;)QPanel;")
	@Test
	public void testValues() throws Exception {
		Panel testSubject;
		List<Value> values = null;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.values(values);
	}

	@MethodRef(name = "addValuesItem", signature = "(QValue;)QPanel;")
	@Test
	public void testAddValuesItem() throws Exception {
		Panel testSubject;
		Value valuesItem = null;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.addValuesItem(valuesItem);
	}

	@MethodRef(name = "getValues", signature = "()QList<QValue;>;")
	@Test
	public void testGetValues() throws Exception {
		Panel testSubject;
		List<Value> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getValues();
	}

	@MethodRef(name = "setValues", signature = "(QList<QValue;>;)V")
	@Test
	public void testSetValues() throws Exception {
		Panel testSubject;
		List<Value> values = null;

		// default test
		testSubject = createTestSubject();
		testSubject.setValues(values);
	}

	@MethodRef(name = "actions", signature = "(QList<QAction;>;)QPanel;")
	@Test
	public void testActions() throws Exception {
		Panel testSubject;
		List<Action> actions = null;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.actions(actions);
	}

	@MethodRef(name = "addActionsItem", signature = "(QAction;)QPanel;")
	@Test
	public void testAddActionsItem() throws Exception {
		Panel testSubject;
		Action actionsItem = null;
		Panel result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.addActionsItem(actionsItem);
	}

	@MethodRef(name = "getActions", signature = "()QList<QAction;>;")
	@Test
	public void testGetActions() throws Exception {
		Panel testSubject;
		List<Action> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getActions();
	}

	@MethodRef(name = "setActions", signature = "(QList<QAction;>;)V")
	@Test
	public void testSetActions() throws Exception {
		Panel testSubject;
		List<Action> actions = null;

		// default test
		testSubject = createTestSubject();
		testSubject.setActions(actions);
	}

	@MethodRef(name = "equals", signature = "(Qjava.lang.Object;)Z")
	@Test
	public void testEquals() throws Exception {
		Panel testSubject;
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
		Panel testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.hashCode();
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		Panel testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}


}
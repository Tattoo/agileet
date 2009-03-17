package pokkare.tests;

import junit.framework.TestCase;
import pokkare.action.AddEventAction;
import java.util.HashMap;
import java.util.Date;

public class AddEventActionTest extends TestCase {

	private AddEventAction action;
	
	protected void setUp() throws Exception {
		action = new AddEventAction();
	}

	protected void tearDown() throws Exception {
		action = null;
	}
	
	public void testGetHosts(){
		assertEquals(action.getHosts().size(), 2);
	}
	public void testSetHosts(){
		HashMap<Integer, String> test = new HashMap<Integer, String>();
		test.put(0, "foofoo");
		action.setHosts(test);
		assertSame(action.getHosts().get(0), "foofoo");
	}
	public void testDescAccessors(){
		action.setDesc("this is description");
		assertSame(action.getDesc(), "this is description");
	}
	public void testTimeAccessors(){
		Date time = new Date(2009,1,1);
		action.setTime(time);
		Date time2 = new Date(2009,1,1);
		assertEquals(action.getTime(), time2);
	}
	public void testHostAccessors(){
		action.setHost(123);
		assertEquals(action.getHost(), new Integer(123));
	}
	public void testExecute(){
		assertEquals(action.execute(), "success");
		action.setTime(new Date(2009,1,1));
		assertEquals(action.execute(), "index");
	}
}

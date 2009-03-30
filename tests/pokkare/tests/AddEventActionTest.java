package pokkare.tests;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import junit.framework.TestCase;
import pokkare.action.AddEventAction;

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
	public void testTimeAccessors() throws ParseException {
		action.setTime("03/06/2009");
		Date time1 = action.dateParser.parse(action.getTime());
		Date time2 = new Date(2009,06,03);
//		if (true)
//			throw new IllegalStateException("" + time1.getYear() + " " + time2.getYear());
		assertEquals(time1, time2);
	}
	public void testHostAccessors(){
		action.setHost(123);
		assertEquals(action.getHost(), new Integer(123));
	}
	public void testExecute(){
		assertEquals(action.execute(), "success");
		action.setTime("01/01/2009");
		assertEquals(action.execute(), "index");
	}
}

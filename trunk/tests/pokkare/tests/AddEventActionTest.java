package pokkare.tests;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		action.setTime("12/30/2009");
		Date time1 = action.dateParser.parse(action.getTime());
		// from API: Month is 0-based. e.g., 0 for January
		GregorianCalendar cal = new GregorianCalendar(2009, 11, 30); 
        assertEquals(cal.getTime(), time1);
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

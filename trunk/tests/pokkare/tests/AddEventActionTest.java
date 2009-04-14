package pokkare.tests;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import junit.framework.TestCase;
import pokkare.action.AddEventAction;
import pokkare.model.Player;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

public class AddEventActionTest extends TestCase {
	EventService event = new EventService();

	private AddEventAction action;
	
	protected void setUp() throws Exception {
		action = new AddEventAction();
	}

	protected void tearDown() throws Exception {
		action = null;
	}
	
	public void testGetHosts() {
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();
		for (int i = 0; i < players.size(); ++i) {
			if (players.get(i).getState() == 'D') {
				players.remove(i);
				i -= 1;
			}
		}
			
		assertEquals(action.getHosts().size(), players.size());
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
		assertEquals(action.execute(), "addevent");
		action.setTime("01/01/2009");
		assertEquals(action.execute(), "success");
		
		ArrayList<String> a = (ArrayList<String>)action.getActionMessages();
		assertEquals(a.get(0), ActionMessages.GAME_ADDED);
		
		action.setTime("69-69-6969");
		assertEquals(action.execute(), "error");
	
		ArrayList<String> e = (ArrayList<String>)action.getActionErrors();
		assertEquals(e.get(0), ErrorMessages.DATE_NOT_PROCESSABLE);
	}
}

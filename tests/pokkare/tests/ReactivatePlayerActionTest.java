package pokkare.tests;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;

import pokkare.action.ReactivatePlayerAction;
import pokkare.model.Player;

import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

public class ReactivatePlayerActionTest extends TestCase {
	ReactivatePlayerAction action;
	EventService event = new EventService();
	
	protected void setUp() throws Exception {
		action = new ReactivatePlayerAction();
	}
	
	protected void tearDown() throws Exception {
		action = null;
	}
	
	public void testParametersAccessors(){
		assertNull(action.getParameters());

		HashMap<String, String[]> param = new HashMap<String, String[]>();
		String[] value = {"bar"};
		String key = "foo";
		param.put(key, value);
		
		action.setParameters(param);
		
		assertEquals(1, action.getParameters().size());
		assertTrue(action.getParameters().containsKey(key));
		assertTrue(action.getParameters().containsValue(value));
		assertTrue(action.getParameters().get(key).equals(value));
		assertEquals("bar", ((String[])action.getParameters().get(key))[0]);
		
		action.setParameters(null); // cleanup after
	}
	
	public void testExecute(){
		try{
			action.execute();
			fail("execute() should've failed with NullPointerException in ReactivatePlayerActionTest");
		} catch (NullPointerException e){ // things okay, put rest of the test here inside the catch
			HashMap<String, String[]> param = new HashMap<String, String[]>();
			String playerName = "Rafael Tommasi";
			String[] value = {playerName};
			param.put("reactivate_player_name", value);
			
			action.setParameters(param);
			
			assertEquals("player_not_found", action.execute());
			//get action error messages. 
			ArrayList<String> a = (ArrayList<String>)action.getActionErrors();
			assertEquals(a.get(0), ErrorMessages.PLAYER_BY_THIS_NAME_NOT_FOUND);
			assertTrue(addTestData(playerName));
			
			ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();
			Player p = null;
			for (int i = 0; i < players.size(); ++i) {
				if (players.get(i).getName().equals(playerName)) {
					p = players.get(i);
				}
			}
			assertTrue(p != null && p.getState() == 'N');
			assertEquals("success", action.execute());
			
			a = (ArrayList<String>)action.getActionMessages();
			assertEquals(a.get(0), ActionMessages.PLAYER_REACTIVATED);
			
			event.deletePlayerRowFromDatabase(action.getReactivatePlayerByPlayerName(playerName)); //cleanup
			action.setParameters(null); // cleanup
		}
	}
	private boolean addTestData(String playerName){
		// add stuff to db 
		Player player = new Player();
		player.setId(999999999);
		player.setName(playerName);
		try {
			event.savePlayer(player);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
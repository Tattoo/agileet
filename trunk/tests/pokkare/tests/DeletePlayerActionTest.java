package pokkare.tests;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;

import pokkare.action.DeletePlayerAction;
import pokkare.model.Player;

import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

public class DeletePlayerActionTest extends TestCase {
	DeletePlayerAction action;
	
	protected void setUp() throws Exception {
		action = new DeletePlayerAction();
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
			fail("execute() should've failed with NullPointerException in DeletePlayerActionTest");
		} catch (NullPointerException npe){ // things okay, put rest of the test here inside the catch
			HashMap<String, String[]> param = new HashMap<String, String[]>();
			String playerName = "Rafael Tommasi";
			String[] value = {playerName};
			param.put("delete_player_name", value);
			
			action.setParameters(param);
			
			assertEquals("player_not_found", action.execute());
			
			ArrayList<String> e = (ArrayList<String>)action.getActionErrors();
			assertEquals(e.get(0), ErrorMessages.PLAYER_BY_THIS_NAME_NOT_FOUND);
			
			assertTrue(addTestData(playerName));
			assertEquals("success", action.execute());
			
			ArrayList<String> a = (ArrayList<String>)action.getActionMessages();
			assertEquals(a.get(0), ActionMessages.PLAYER_DELETED);
			
			assertTrue(action.deletePlayerRowFromDatabase(action.getDeletablePlayerByPlayerName(playerName))); //cleanup
			action.setParameters(null); // cleanup
		}
	}
	private boolean addTestData(String playerName){
		// add stuff to db 
		EventService event = new EventService();
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
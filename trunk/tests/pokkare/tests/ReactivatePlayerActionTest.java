package pokkare.tests;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;

import pokkare.action.DeletePlayerAction;
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
	
	// System.out.println();
	public void testPlayersAccessors(){
		assertEquals(new ArrayList<String>(), action.getPlayers());
		String playerName = "Reactivate player action test";
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(playerName);
		action.setPlayers(null);
		assertNull(action.getPlayers());
		action.setPlayers(arr);
		assertNotNull(action.getPlayers());
		assertEquals(1, action.getPlayers().size());
		assertEquals(playerName, action.getPlayers().get(0));
	}
	
	public void testStateDPlayers(){
		assertEquals(new ArrayList<String>(), action.getStateDPlayers());
		String deletedPlayerName = "Reactivate player action test";
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(deletedPlayerName);
		action.setStateDPlayers(null);
		assertNull(action.getStateDPlayers());
		action.setStateDPlayers(arr);
		assertNotNull(action.getStateDPlayers());
		assertEquals(1, action.getStateDPlayers().size());
		assertEquals(deletedPlayerName, action.getStateDPlayers().get(0));
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
		try {
			action.execute();
			fail("ReactivatePlayerAction should've failed with NullPointerException, didn't...");
		} catch (NullPointerException e){ // okay, put the rest of the test inside the catch
			HashMap<String, String[]> param = new HashMap<String, String[]>();			
			param.put("reactivate_player_name", null);
			action.setParameters(param);
			assertEquals("error", action.execute()); 
			ArrayList<String> a = (ArrayList<String>)action.getActionErrors();
			assertEquals(ErrorMessages.NO_PLAYER_IN_REQUEST, a.get(0));
			action.setActionErrors(null); // clear error messages
			
			param.clear();
			String playerName = "reactivate player action test";
			String[] value = {playerName};
			param.put("reactivate_player_name", value);
			action.setParameters(param);
			assertEquals("player_not_found", action.execute());
			
			//get action error messages. 
			a = (ArrayList<String>)action.getActionErrors();
			assertEquals(ErrorMessages.PLAYER_BY_THIS_NAME_NOT_FOUND, a.get(0));
			assertTrue(addTestData(playerName));

			Player p = null;
			for (Player pl : (ArrayList<Player>)event.findPlayers()){
				if (pl.getName().compareTo(playerName) == 0){
					p = pl;
				}
			}
			assertTrue(p != null && p.getState() == 'N');
			event.deletePlayer(p); // delete player so we can reactivate
			assertEquals("success", action.execute());

			a = (ArrayList<String>)action.getActionMessages();
			assertEquals(a.get(0), ActionMessages.PLAYER_REACTIVATED);

			event.deletePlayerRowFromDatabase(p); //cleanup
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
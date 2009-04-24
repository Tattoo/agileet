package pokkare.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import pokkare.action.EditRankingAction;
import pokkare.model.Games;
import junit.framework.TestCase;


public class EditRankingActionTest extends TestCase {
	EditRankingAction action;
	HashMap<String, String[]> testparam;
	String key, valuecell;
	String[] value;

	protected void setUp() throws Exception {
		action = new EditRankingAction();	
		testparam = new HashMap<String, String[]>();
		key = "testkey";
		value = new String[1];
		valuecell = new String("testvalue");
		value[0] = valuecell;
		testparam.put(key, value);
	}
	
	protected void tearDown() throws Exception {
		action = null;
		testparam = null;
		key = null;
		valuecell = null;
		value = null;
	}
	
	public void testChosenGameAccessors(){
		assertEquals(new Integer(-1), action.getChosenGame());
		action.setChosenGame(999);
		assertEquals(new Integer(999), action.getChosenGame());
	}
	
	public void testParametersAccessors(){
		assertNull(action.getParameters());
		action.setParameters(testparam);
		assertNotNull(action.getParameters());
		assertEquals(testparam, action.getParameters());
		assertEquals(1, action.getParameters().size());
		assertTrue(action.getParameters().containsKey(key));
		assertTrue(action.getParameters().containsValue(value));
		
	}
	
	public void testChosenGameDescAccessors(){
		assertEquals("", action.getChosenGameDesc());
		String test = "test chosen game desc";
		action.setChosenGameDesc(test);
		assertEquals(test, action.getChosenGameDesc());
	}
	
	public void testGamesAccessors(){
		assertNull(action.getGames());
		ArrayList<Games> testgames = new ArrayList<Games>();
		Games test = new Games();
		String testdesc = "test games description";
		test.setDescription(testdesc);
		Date testdate = new Date(1900, 0, 1);
		test.setGameDate(testdate);
		Integer testnumber = new Integer(999999999);
		test.setGameNumber(testnumber);
		test.setGameScoreId(testnumber);
		test.setHost(testnumber);
		test.setId(testnumber);
		testgames.add(test);
		action.setGames(testgames);
		assertNotNull(action.getGames());
		assertEquals(testgames, action.getGames());
		assertEquals(1, action.getGames().size());
		assertEquals(test, action.getGames().get(0));
		assertEquals(testdesc, action.getGames().get(0).getDescription());
		assertEquals(testdate, action.getGames().get(0).getGameDate());
		assertEquals(testnumber, action.getGames().get(0).getGameNumber());
		assertEquals(testnumber, action.getGames().get(0).getGameScoreId());
		assertEquals(testnumber, action.getGames().get(0).getHost());
		assertEquals(testnumber, action.getGames().get(0).getId());
	}
	
	public void testPlayerScoresAccessors(){
		assertNull(action.getPlayerScores());
		HashMap<String, Integer> test = new HashMap<String, Integer>();
		String key = "test player scores";
		Integer value = new Integer(999999999);
		test.put(key, value);
		action.setPlayerScores(test);
		assertNotNull(action.getPlayerScores());
		assertEquals(test, action.getPlayerScores());
		assertTrue(action.getPlayerScores().containsKey(key));
		assertTrue(action.getPlayerScores().containsValue(value));
		assertEquals(value, action.getPlayerScores().get(key));
	}
	
	public void testExecute(){
		try {
			action.execute();
			fail("EditRankingActions should've failed in EditRankingActionTest");
		} catch (IllegalStateException e){ // things okay, put rest of the test case inside the catch
			
			action.setParameters(null);
			action.setParameters(testparam);
			
			assertEquals("error", action.execute());
			assertEquals("[Tapahtui sis&auml;inen virhe.]", action.getActionErrors().toString());
			
			action.setActionErrors(null);
			testparam.clear();
			
			assertEquals("pickgame", action.execute());
			
			String[] testvalue = {"1"};
			testparam.put("chosenGame", testvalue);
			action.setParameters(testparam);
			action.setChosenGame(1);
			assertEquals("editscores", action.execute());
			
			testvalue[0] = "0";
			testparam.put("jouns", testvalue);
			assertEquals("success", action.execute());
			
		}

	}
}
package pokkare.tests;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import pokkare.action.AddRankingAction;
import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.EventService;

public class AddRankingActionTest extends TestCase {

	private AddRankingAction ranking;
	private HashMap<String, String[]> testparams;
	private EventService event;
	
	protected void setUp() throws Exception {
		ranking = new AddRankingAction();
		testparams = new HashMap<String, String[]>();
		String[] testranking1 = {"1"};
		String[] testrankin2 = {"2"};
		testparams.put("jouns", testranking1);
		testparams.put("tattoo", testrankin2);
	}

	protected void tearDown() throws Exception {
		ranking = null;
		testparams = null;
	}
	
	public void testPlayerListAccessors(){
		ArrayList<Player> temp = new ArrayList<Player>();
		Player p = new Player();
		p.setId(999);
		p.setName("Foo Foo");
		temp.add(p);
		ranking.setPlayerList(temp);
		assertEquals(1, ranking.getPlayerList().size());
		assertSame(p, ranking.getPlayerList().get(0));
	}
	
	// suppressed warning about accessing static method un-static way 
	@SuppressWarnings("static-access")
	public void testGetChosenGame(){
		assertEquals(ranking.getChosenGame(), new Integer(-1));
	}
	
	// suppressed warning about accessing static method un-static way	
	@SuppressWarnings("static-access")
	public void testSetChosenGame(){
		testGetChosenGame();
		// save game so we can tear this case down
		Integer game = ranking.getChosenGame();  
		ranking.setChosenGame(new Integer(999));
		assertEquals(new Integer(999), ranking.getChosenGame());
		ranking.setChosenGame(game);
		testGetChosenGame(); // test that tear down was successful
	}
	
	public void testGamesListAccessors(){
		assertEquals(new HashMap<Integer, String>(), ranking.getGamesMap());
		HashMap<Integer, String> hashmap = new HashMap<Integer, String>();
		hashmap.put(0, "Bar Bar");
		ranking.setGamesMap(hashmap);
		assertEquals(hashmap, ranking.getGamesMap());
	}
	
	public void testExecute_ChosenGameLessThanZero(){
		assertEquals(ranking.execute(), "success");
	}
	
	public void testParametersAccessors(){
		assertEquals(null, ranking.getParameters());
		ranking.setParameters(testparams);
		assertEquals(testparams, ranking.getParameters());
	}

	// TODO: this test case must be able to remove the entry from db
	// 		before we can run it
	/*
	public void testExecute_rest(){
		// execute tests we're depending on
		testParametersAccessors();
		ranking.setChosenGame(new Integer(999));
		ranking.setParameters(testparams);
		assertEquals("index", ranking.execute());
	}
	*/
}

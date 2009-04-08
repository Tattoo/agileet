package pokkare.tests;
import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;

import org.hibernate.Session;

import pokkare.action.AddRankingAction;
import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;
import pokkare.service.HibernateUtil;

public class AddRankingActionTest extends TestCase {

	private AddRankingAction action;
	private HashMap<String, String[]> testparams;
	private EventService event;
	
	protected void setUp() throws Exception {
		action = new AddRankingAction();
		testparams = new HashMap<String, String[]>();
		String[] testranking1 = {"1"};
		String[] testrankin2 = {"2"};
		testparams.put("jouns", testranking1);
		testparams.put("tattoo", testrankin2);
	}

	protected void tearDown() throws Exception {
		action = null;
		testparams = null;
	}
	
	public void testPlayerListAccessors(){
		ArrayList<Player> temp = new ArrayList<Player>();
		Player p = new Player();
		p.setId(999);
		p.setName("Foo Foo");
		temp.add(p);
		action.setPlayerList(temp);
		assertEquals(1, action.getPlayerList().size());
		assertSame(p, action.getPlayerList().get(0));
	}
	
	// suppressed warning about accessing static method un-static way 
	@SuppressWarnings("static-access")
	public void testGetChosenGame(){
		assertEquals(action.getChosenGame(), new Integer(-1));
	}
	
	// suppressed warning about accessing static method un-static way	
	@SuppressWarnings("static-access")
	public void testSetChosenGame(){
		testGetChosenGame();
		// save game so we can tear this case down
		Integer game = action.getChosenGame();  
		action.setChosenGame(new Integer(999));
		assertEquals(new Integer(999), action.getChosenGame());
		action.setChosenGame(game);
		testGetChosenGame(); // test that tear down was successful
	}
	
	public void testGamesListAccessors(){
		assertEquals(new HashMap<Integer, String>(), action.getGamesMap());
		HashMap<Integer, String> hashmap = new HashMap<Integer, String>();
		hashmap.put(0, "Bar Bar");
		action.setGamesMap(hashmap);
		assertEquals(hashmap, action.getGamesMap());
	}
	
	public void testExecute_ChosenGameLessThanZero(){
		assertEquals(action.execute(), "addranking");
	}
	
	public void testParametersAccessors(){
		assertEquals(null, action.getParameters());
		action.setParameters(testparams);
		assertEquals(testparams, action.getParameters());
	}

	public void testExecute_rest(){
		// execute tests we're depending on
		testParametersAccessors();		
		Integer chosenGame = new Integer(999999999);
		action.setChosenGame(chosenGame);
		action.setParameters(testparams);
		
		assertEquals("success", action.execute());
		
		ArrayList<String> a = (ArrayList<String>)action.getActionMessages();
		assertEquals(a.get(0), ActionMessages.RANKING_ADDED);
		
		assertEquals(true, deleteTestData(chosenGame));
	}
	
	private boolean deleteTestData(Integer chosenGame){
		// delete stuff that was put in db during this test case
		event = new EventService();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			for (Score s : event.findScores()){
				if (s.getGameScoreId().equals(chosenGame)){
					session.delete(s);
				}
			}
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			if (session != null && session.isOpen()){
				session.close();
			}
			return false;
		}
	}
}

package pokkare.tests;
import java.util.ArrayList;

import junit.framework.TestCase;
import pokkare.action.IndexAction;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;

public class IndexActionTest extends TestCase {

	private IndexAction action;
	
	protected void setUp() throws Exception {
		action = new IndexAction();
	}
	
	protected void tearDown() throws Exception  {
		action = null;
	}
	
//	public void testEmptlyPlayers() {
//		assertEquals("error", action.execute());
//		
//		ArrayList<String> e = (ArrayList<String>)action.getActionErrors();
//		assertEquals(e.get(0), ErrorMessages.NO_PLAYERS_AVAILABLE);
//	}
	
	public void testRankingAccessors(){
		assertEquals(new ArrayList<String>(), action.getRanking());
		ArrayList<String> ranking = new ArrayList<String>();
		ranking.add("1");
		ranking.add("2");
		ranking.add("3");
		action.setRanking(ranking);
		assertEquals(3, action.getRanking().size());
		for (int i = 0; i < ranking.size(); i++){
			assertEquals(ranking.get(i), action.getRanking().get(i));
		}
	}
	
	public void testExecute(){
		assertEquals("success", action.execute());
	}
}

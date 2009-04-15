package pokkare.tests;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import pokkare.action.IndexAction;
import pokkare.model.Player;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

public class IndexActionTest extends TestCase {

	private IndexAction action;
	
	protected void setUp() throws Exception {
		action = new IndexAction();
	}
	
	protected void tearDown() throws Exception  {
		action = null;
	}
	
	public void testEventServiceAccessors(){
		assertNotNull(action.getEvent());
		EventService temp = action.getEvent();
		action.setEvent(null);
		assertNull(action.getEvent());
		action.setEvent(temp);
		assertNotNull(action.getEvent());
	}
	
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
		EventService temp = action.getEvent();
		action.setEvent(new MockEventService());
		assertEquals("error", action.execute());
		action.setEvent(temp);
		assertEquals("success", action.execute());
	}

	/*
	 * Helpers
	 * */
	
	private class MockEventService extends EventService{

		@Override
		public List<Player> findPlayers() {
			return null;
		}
		
	}
}

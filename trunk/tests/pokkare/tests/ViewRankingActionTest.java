package pokkare.tests;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;
import pokkare.action.ViewRankingAction;
import pokkare.action.ViewRankingAction.GameDataObject;
import pokkare.service.ScoreDataWrapper;
import pokkare.service.ScoreDataWrapper.ScoreData;

public class ViewRankingActionTest extends TestCase {
	ViewRankingAction view;
	protected void setUp() throws Exception {
		view = new ViewRankingAction();
	}

	protected void tearDown() throws Exception {
		view = null;
	}

	public void testGameDataObject(){
		GameDataObject o = view.new GameDataObject(999, new Date(1900,1,1), 999);
		Integer mockPosition = 888, mockGameNumber = 888;
		Date mockDate = new Date(1999, 11, 12);
		
		assertEquals(999, o.getPositionInSeries());
		assertEquals(new Date(1900,1,1), o.getGameDate());
		assertEquals(999, o.getGameNumber());
		
		o.setGameDate(mockDate);
		assertEquals(mockDate, o.getGameDate());
		
		o.setGameNumber(mockGameNumber);
		assertEquals(mockGameNumber.intValue(), o.getGameNumber());
		
		o.setPositionInSeries(mockPosition);
		assertEquals(mockPosition.intValue(), o.getPositionInSeries());
		
		o = null; // cleanup
	}
	
	public void testSizeAccessors(){
		assertNull(view.getSize());
		String size = "11";
		view.setSize(size);
		assertEquals(size, view.getSize());
	}
	
	public void testGamesAccessors(){
		assertNull(view.getGames());
		HashMap<Integer, String> hash = new HashMap<Integer, String>();
		hash.put(999, "Test Games Accessors in View Ranking Actin Test");
		view.setGames(hash);
		assertNotNull(view.getGames());
		assertSame(hash, view.getGames());
	}
	
	public void testGamesListAccessors(){
		assertNull(view.getGamesList());
		ArrayList<GameDataObject> arr = new ArrayList<GameDataObject>();
		GameDataObject mock = view.new GameDataObject(999, new Date(1900,1,1), 999);
		arr.add(mock);
		view.setGamesList(arr);
		assertNotNull(view.getGamesList());
		assertSame(arr, view.getGamesList());
	}
	
	public void testRankingAccessors(){
		assertEquals(new ArrayList<String>(), view.getRanking());
		ArrayList<String> testRanking = new ArrayList<String>();
		testRanking.add("1");
		testRanking.add("2");
		testRanking.add("3");
		view.setRanking(testRanking);
		assertEquals(testRanking, view.getRanking());
	}
	
	public void testExecute(){
		String response = null;
		try {
			response = view.execute();
		} catch (Exception e){
			e.printStackTrace();
			fail("testExecute() threw an exception");
		} finally {
			assertEquals("success", response);
		}
	}
	public void testScoreDatasAccessors(){
		assertNotNull(view.getScoreDatas());
		List<ScoreData> temp = view.getScoreDatas();
		view.setScoreDatas(null);
		assertNull(view.getScoreDatas());
		view.setScoreDatas(temp);
		assertSame(temp, view.getScoreDatas());
	}
	
	public void testScoreDataWrapperAccessors(){
		assertNotNull(view.getScoreDataWrapper());
		ScoreDataWrapper temp = view.getScoreDataWrapper();
		view.setScoreDataWrapper(null);
		assertNull(view.getScoreDataWrapper());
		view.setScoreDataWrapper(temp);
		assertSame(temp, view.getScoreDataWrapper());
	}
	
	public void testScoresAccessors(){
		assertNull(view.getScores());
		HashMap<String, HashMap<Integer, Integer>> test = new HashMap<String, HashMap<Integer, Integer>>();
		HashMap<Integer, Integer> value = new HashMap<Integer, Integer>();
		value.put(123,456);
		String key = "Hash Map Key";
		test.put(key, value);
		view.setScores(test);
		assertNotNull(view.getScores());
		assertEquals(test, view.getScores());
		assertEquals(1, view.getScores().size());
	}
}

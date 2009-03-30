package pokkare.tests;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import pokkare.service.ScoreDataWrapper;
import pokkare.service.ScoreDataWrapper.ScoreData;
import pokkare.model.Player;
import pokkare.model.Games;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
/*
 * Also tests ScoreDataWrapper's inner class ScoreData
 * */

public class ScoreDataWrapperTest extends TestCase {
	private ScoreDataWrapper dw;
	private Player p;
	private Games g;
	protected void setUp() throws Exception {
		dw = new ScoreDataWrapper();
	}

	protected void tearDown() throws Exception {
		dw = null;
	}
	
	private ScoreData getTestScoreData(){
		p = new Player();
		p.setId(123456789);
		String name = "Test Score Data";
		p.setName(name);
		
		g = new Games();
		String desc = "Test Score Data Description";
		g.setDescription(desc);
		Date date = new Date(3000,1,1);
		g.setGameDate(date);
		g.setGameNumber(123456789);
		g.setGameScoreId(987654321);
		g.setHost(p.getId());
		g.setId(123456789);
		
		return dw.new ScoreData(p, g, 999, 1, 1);
		
	}
	
	public void testScoreData(){
		ScoreData sd = getTestScoreData();
		assertNotNull(sd);
		assertEquals(g, sd.getGame());
		assertEquals(p, sd.getPlayer());
		assertEquals(999, sd.getScore());
	}
	
	public void testGetScoreDatas(){
		assertNotNull(dw.getScoreDatas());
		try { // TODO: no assertNotEquals... ? there must be smarter way to do this
			assertEquals(new ArrayList<ScoreData>(), dw.getScoreDatas()); // expect to throw AssertionFailedError 
			fail("getScoreDatas() was empty");
		} catch (AssertionFailedError e){
			ArrayList<ScoreData> list = new ArrayList<ScoreData>();
			list.add(getTestScoreData());
			dw.setScoreDatas(list);
			assertNotNull(dw.getMaxScore());
			assertNotNull(dw.getNumberOfGames());
			assertEquals(list.size(), dw.getScoreDatas().size());
			assertEquals(list.get(0), dw.getScoreDatas().get(0));
			
		}
	}

	
}

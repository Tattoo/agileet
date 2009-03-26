package pokkare.tests;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import pokkare.service.ScoreDataWrapper;
import pokkare.service.ScoreDataWrapper.ScoreData;
import pokkare.model.Player;
import pokkare.model.Games;
import java.util.Date;
import java.util.ArrayList;
/*
 * Also tests ScoreDataWrapper's inner class ScoreData
 * */

public class ScoreDataWrapperTest extends TestCase {
	private ScoreDataWrapper dw;
	private ScoreData scoredata1;
	private ScoreData scoredata2;
	protected void setUp() throws Exception {
		dw = new ScoreDataWrapper();
		
		Player p = new Player();
		p.setId(999999999);
		p.setName("Foo Foo");
		
		Player pl = new Player();
		pl.setId(999999998);
		pl.setName("Bar Bar");
		
		Games g = new Games();
		g.setDescription("Test game test");
		g.setGameDate(new Date(1900,1,1));
		g.setGameNumber(999999999);
		g.setGameScoreId(999999999);
		g.setHost(p.getId());
		g.setId(999999999);
		
		scoredata1 = dw.new ScoreData(p, g, 20);
		scoredata2 = dw.new ScoreData(pl, g, 10);
		
		dw.getScoreDatas().add(scoredata1);
		dw.getScoreDatas().add(scoredata2);
		
		p = null;
		pl = null;
		g = null;
	}

	protected void tearDown() throws Exception {
		scoredata1 = null;
		scoredata2 = null;
		dw = null;
	}
	
	public void testScoreData(){
		Player p = new Player();
		p.setId(123456789);
		String name = "Test Score Data";
		p.setName(name);
		
		Games g = new Games();
		String desc = "Test Score Data Description";
		g.setDescription(desc);
		Date date = new Date(3000,1,1);
		g.setGameDate(date);
		g.setGameNumber(123456789);
		g.setGameScoreId(987654321);
		g.setHost(p.getId());
		g.setId(123456789);
		
		ScoreData sd = dw.new ScoreData(p, g, 999);
		
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
			// insert rest of the assertions here instead of out of catch
			assertEquals(scoredata1, dw.getScoreDatas().get(0));
			assertEquals(scoredata2, dw.getScoreDatas().get(1));
		}
	}

	
}

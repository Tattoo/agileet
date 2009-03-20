package pokkare.tests;
import java.util.Date;

import junit.framework.TestCase;
import pokkare.model.Games;
public class GamesTest extends TestCase {
	Games testgames;
	protected void setUp() throws Exception {
		testgames = new Games();
	}

	protected void tearDown() throws Exception {
		testgames = null;
	}

	public void testIdAccessors(){
		assertNull(testgames.getId());
		testgames.setId(1);
		assertEquals(new Integer(1), testgames.getId());
	}
	
	public void testDescriptionAccessors(){
		assertNull(testgames.getDescription());
		String desc = "this is a description.";
		testgames.setDescription(desc);
		assertEquals(desc, testgames.getDescription());
	}
	
	public void testHostAccessors(){
		assertNull(testgames.getHost());
		testgames.setHost(1);
		assertEquals(new Integer(1), testgames.getHost());
	}
	
	public void testGameDateAccessors(){
		assertNull(testgames.getGameDate());
		Date d = new Date(new Long(0)); // this is epoch
		testgames.setGameDate(d);
		assertEquals(d, testgames.getGameDate());
	}
	
	public void testGameNumberAccessors(){
		assertNull(testgames.getGameNumber());
		testgames.setGameNumber(1);
		assertEquals(new Integer(1), testgames.getGameNumber());
	}
	
	public void testGameScoreIdAccessors(){
		assertNull(testgames.getGameScoreId());
		testgames.setGameScoreId(1);
		assertEquals(new Integer(1), testgames.getGameScoreId());
	}
}

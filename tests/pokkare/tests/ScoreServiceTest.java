package pokkare.tests;
import java.util.HashMap;

import junit.framework.TestCase;
import pokkare.service.ScoreService;

public class ScoreServiceTest extends TestCase {
	private ScoreService ss;
	
	protected void setUp() throws Exception {
		ss = new ScoreService();
	}

	protected void tearDown() throws Exception {
		ss = null;
	}

	public void testGetPlayerScores(){
		// TODO: this tests that the code works as it's now implemented
		// THIS TEST WILL BREAK WHEN ScoreService is implemented to (actually) 
		// do something (and it should!)
		assertEquals(new HashMap<Integer, Integer>(),ss.getPlayerScores());	
	}
}

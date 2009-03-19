package pokkare.tests;
import junit.framework.TestCase;
import pokkare.model.Score;

public class ScoreTest extends TestCase {

	private Score score;
	
	protected void setUp() throws Exception {
		score = new Score();
	}

	protected void tearDown() throws Exception {
		score = null;
	}

	public void testIdAccessors(){
		assertNull(score.getId());
		score.setId(1);
		assertEquals(new Integer(1), score.getId());
	}
	
	public void testGameScoreIdAccessors(){
		assertNull(score.getGameScoreId());
		score.setGameScoreId(1);
		assertEquals(new Integer(1), score.getGameScoreId());
	}
	
	public void testPlayerIdAccessors(){
		assertNull(score.getPlayerId());
		score.setPlayerId(1);
		assertEquals(new Integer(1), score.getPlayerId());
	}
	
	public void testRankAccessors(){
		assertNull(score.getRank());
		score.setRank(1);
		assertEquals(new Integer(1), score.getRank());
	}
}

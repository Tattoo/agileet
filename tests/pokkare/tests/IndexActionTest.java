package pokkare.tests;
import java.util.ArrayList;

import junit.framework.TestCase;
import pokkare.action.IndexAction;

public class IndexActionTest extends TestCase {

	private IndexAction index;
	
	protected void setUp() throws Exception {
		index = new IndexAction();
	}
	
	protected void tearDown() throws Exception  {
		index = null;
	}
	
	public void testRankingAccessors(){
		assertEquals(new ArrayList<String>(), index.getRanking());
		ArrayList<String> ranking = new ArrayList<String>();
		ranking.add("1");
		ranking.add("2");
		ranking.add("3");
		index.setRanking(ranking);
		assertEquals(3, index.getRanking().size());
		for (int i = 0; i < ranking.size(); i++){
			assertEquals(ranking.get(i), index.getRanking().get(i));
		}
	}
	
	public void testExecute(){
		assertEquals("success", index.execute());
	}
}

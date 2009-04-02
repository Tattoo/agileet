package pokkare.tests;
import java.util.ArrayList;

import junit.framework.TestCase;
import pokkare.action.ViewRankingAction;

public class ViewRankingActionTest extends TestCase {
	ViewRankingAction view;
	protected void setUp() throws Exception {
		view = new ViewRankingAction();
	}

	protected void tearDown() throws Exception {
		view = null;
	}

	public void testSizeAccessors(){
		assertNull(view.getSize());
		String size = "11";
		view.setSize(size);
		assertEquals(size, view.getSize());
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
}

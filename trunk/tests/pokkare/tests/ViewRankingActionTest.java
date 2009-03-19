package pokkare.tests;
import junit.framework.TestCase;
import pokkare.action.ViewRankingAction;
import java.util.ArrayList;

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
	/* TODO: When graph-drawing works, uncomment
	 * 
	 
	public void testDrawPokkareGraph(){
		try {
			view.drawPokkareGraph(999);
		} catch (Exception e){
			e.printStackTrace();
			fail("testDrawPokkareGraph() threw an exception");
		}
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
	*/
}
package pokkare.tests;
import junit.framework.TestCase;
import pokkare.model.Points;

public class PointsTest extends TestCase {
	private Points points;
	protected void setUp() throws Exception {
		points = new Points();
	}

	protected void tearDown() throws Exception {
		points = null;
	}
	
	public void testRankAccessors(){
		assertNull(points.getRank());
		points.setRank(1);
		assertEquals(new Integer(1), points.getRank());
	}
	
	public void testPointsAccessors(){
		assertNull(points.getPoints());
		points.setPoints(1);
		assertEquals(new Integer(1), points.getPoints());
	}

}

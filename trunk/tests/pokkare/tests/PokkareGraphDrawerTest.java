package pokkare.tests;
import java.io.FileOutputStream;

import junit.framework.TestCase;

import pokkare.service.PokkareGraphDrawer;

public class PokkareGraphDrawerTest extends TestCase {

	PokkareGraphDrawer graph;
	
	protected void setUp() throws Exception {
		graph = new PokkareGraphDrawer();
	}

	protected void tearDown() throws Exception {
		graph = null;
	}
	
	public void testMaxPointsAccessors(){
		assertEquals(0, graph.getMaxPoints());
		graph.setMaxPoints(1);
		assertEquals(1, graph.getMaxPoints());
	}
	
	public void testMultiplierAccessors(){
		assertEquals(1, graph.getMultiplier());
		graph.setMultiplier(2);
		assertEquals(2, graph.getMultiplier());
	}
	
/* This cannot be run until createImage() works on Linux
 *  	
	public void testCreateImage(){
		try {
			FileOutputStream f = new FileOutputStream("pokkaregraph.jpg");
			assertNotNull(f);
			graph.createImage(f);
			f.close();
		} catch(Exception e) {
			e.printStackTrace();
			fail("testCreateImage() threw an exception");
		}
	}
*/
}

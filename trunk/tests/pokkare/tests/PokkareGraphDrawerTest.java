package pokkare.tests;
import junit.framework.TestCase;

import pokkare.service.PokkareGraphDrawer;
import pokkare.service.ScoreDataWrapper;
import pokkare.service.ScoreDataWrapper.ScoreData;

import java.io.FileOutputStream;

import pokkare.model.Player;
import pokkare.model.Games;

public class PokkareGraphDrawerTest extends TestCase {

	PokkareGraphDrawer graph;
	
	protected void setUp() throws Exception {
		// TODO: correct path
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
		assertEquals(3, graph.getMultiplier());
		graph.setMultiplier(2);
		assertEquals(2, graph.getMultiplier());
	}

	public void testCreateGraphs(){
		ScoreDataWrapper dw = new ScoreDataWrapper();		
		ScoreData sd = dw.new ScoreData(new Player(), new Games(), 999);
		dw.getScoreDatas().add(sd);
		graph.createGraphs(dw, 999, 1);
	}
	
/* This cannot be run until createImage() works on Linux
 *
	public void testCreateImage(){
		try {
			FileOutputStream fo = new FileOutputStream("pokkaregraph.jpg");
			assertNotNull(fo);
			graph.createImage(fo);
			fo.close();
		} catch(Exception e) {
			e.printStackTrace();
			fail("testCreateImage() threw an exception");
		}
	}
*/	
}

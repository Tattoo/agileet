package pokkare.tests;
import junit.framework.TestCase;

import pokkare.model.Player;

public class PlayerTest extends TestCase {
	Player testplayer;
	protected void setUp() throws Exception {
		testplayer = new Player();
	}

	protected void tearDown() throws Exception {
		testplayer = null;
	}
	
	public void testIdAccessors(){
		assertNull(testplayer.getId());
		testplayer.setId(1);
		assertEquals(new Integer(1), testplayer.getId());
	}
	
	public void testNameAccessors(){
		assertNull(testplayer.getName());
		String name = "Foo B'ar Foo";
		testplayer.setName(name);
		assertEquals(name, testplayer.getName());
	}

}

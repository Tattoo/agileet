package pokkare.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for pokkare.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(AddEventActionTest.class);
		suite.addTestSuite(AddRankingActionTest.class);
		suite.addTestSuite(GamesTest.class);
		suite.addTestSuite(PlayerTest.class);
		suite.addTestSuite(ViewRankingActionTest.class);
		suite.addTestSuite(ScoreServiceTest.class);
		suite.addTestSuite(PokkareGraphDrawerTest.class);
		suite.addTestSuite(PointsServiceTest.class);
		suite.addTestSuite(PointsTest.class);
		suite.addTestSuite(IndexActionTest.class);
		suite.addTestSuite(ScoreTest.class);
		suite.addTestSuite(EventServiceTest.class);
		//$JUnit-END$
		return suite;
	}

}

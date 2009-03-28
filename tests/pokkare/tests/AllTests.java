package pokkare.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for pokkare.tests");

		//model tests
		suite.addTestSuite(GamesTest.class);
		suite.addTestSuite(PlayerTest.class);
		suite.addTestSuite(ScoreTest.class);		
		suite.addTestSuite(PointsTest.class);
		
		//service tests
		
		suite.addTestSuite(ScoreDataWrapperTest.class);// MUST BE BEFORE PokkareGraphDrawerTest
		suite.addTestSuite(PointsServiceTest.class); // MUST BE BEFORE PokkareGraphDrawerTest
		suite.addTestSuite(EventServiceTest.class);
		suite.addTestSuite(ScoreServiceTest.class);
		suite.addTestSuite(PokkareGraphDrawerTest.class); // MUST BE AFTER PointsServiceTest AND ScoreDataWrapperTest
				
		// action tests
		suite.addTestSuite(AddEventActionTest.class);
		suite.addTestSuite(AddRankingActionTest.class);
		suite.addTestSuite(ViewRankingActionTest.class);
		suite.addTestSuite(IndexActionTest.class);
		suite.addTestSuite(DeletePlayerActionTest.class); // MUST BE BEFORE AddPLayerActionTest
		suite.addTestSuite(AddPlayerActionTest.class); // MUST BE AFTER DeletePlayerActionTest
		

		return suite;
	}

}

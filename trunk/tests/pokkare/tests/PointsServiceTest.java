package pokkare.tests;

import junit.framework.TestCase;

import org.hibernate.Session;

import pokkare.model.Points;
import pokkare.service.HibernateUtil;
import pokkare.service.PointsService;

public class PointsServiceTest extends TestCase {

	private PointsService pointS;
	private Session session;
	private Points testpoints; 
	
	protected void setUp() throws Exception {
		testpoints = new Points();
		testpoints.setPoints(999999999);
		testpoints.setRank(999999999);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(testpoints);
		session.getTransaction().commit();
		session.close();
	}

	protected void tearDown() throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(testpoints);
		session.getTransaction().commit();
		session.close();
		session = null;
		
		testpoints = null;
	}

	public void testGetScore(){
		/* PointsService must be initialized here so the stuff we're doing to 
		 * db in set up and tear down is there for use
		 */
		pointS = new PointsService(); 
		assertEquals(testpoints.getPoints(), pointS.getScore(testpoints.getRank()));
		pointS = null;
	}
}

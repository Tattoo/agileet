package pokkare.tests;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.model.Points;
import pokkare.model.Score;
import pokkare.service.EventService;
import pokkare.service.HibernateUtil;

public class EventServiceTest extends TestCase {

	private EventService event;
	private Session session;
	private Games testgame;
	private Player testplayerA;
	private Score testscore;
	private Points testpoints;
	private SessionFactory factory;
	
	protected void setUp() throws Exception { 
		event = new EventService();
		factory = event.getSessionFactory();
		
		testplayerA = new Player();
		testplayerA.setId(999999);
		testplayerA.setName("Foo Foo");	
		testpoints = new Points();
		testpoints.setPoints(999999999);
		testpoints.setRank(999999999);

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(testplayerA);
		session.save(testpoints);
		session.getTransaction().commit();

		testgame = new Games();
		testgame.setGameDate(new Date(1900,1,1));
		testgame.setId(999999999);
		testgame.setHost(findByPlayerId(testplayerA.getName()));
		testgame.setGameScoreId(999999999);
		testgame.setGameNumber(999999999);
		session.beginTransaction();
		session.save(testgame);
		session.getTransaction().commit();

		testscore = new Score();
		testscore.setId(999999999);
		testscore.setRank(999999999);
		testscore.setGameScoreId(999999999);
		testscore.setPlayerId(findByPlayerId(testplayerA.getName()));

		session.beginTransaction();			
		session.save(testscore);
		session.getTransaction().commit();
		session.close();

	}

	protected void tearDown() throws Exception {
		event.setSessionFactory(factory); // factory-variable should always be valid
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (Score s : event.findScores()){
			if (s.getGameScoreId().equals(999999999)){
				session.delete(s);
			}
		}
		session.getTransaction().commit();
		
		session.beginTransaction();
		for (Points p : event.findPoints()){
			if (p.getPoints().equals(999999999) || p.getRank().equals(999999999)){
				session.delete(p);
			}
		}
		session.getTransaction().commit();


		session.beginTransaction();
		for (Games g : event.findGames()){
			if (g.getGameNumber().equals(999999999)){
				session.delete(g);
			}
		}
		session.getTransaction().commit();

		session.beginTransaction();
		Query q = session.createQuery("delete from Player where name='"+testplayerA.getName()+"'");
		if(q.executeUpdate() == 0){
			throw new IllegalStateException("tear down didn't remove anything from db");
		}
		session.getTransaction().commit();
		session.close();
		session = null;
		event = null;
		testgame = null;
		testplayerA = null;
		testscore = null;
	}
	
	public void testSessionFactoryAccessors(){
		factory = event.getSessionFactory();
		assertNotNull(factory);
		event.setSessionFactory(null);
		assertNull(event.getSessionFactory());
		event.setSessionFactory(factory);
		assertSame(factory, event.getSessionFactory());
	}

	public void testFindPlayers(){
		// check that findPlayers() handles exceptions
		factory = event.getSessionFactory();
		event.setSessionFactory(new MockSessionFactory());
		assertNotNull(event.findPlayers());
		event.setSessionFactory(factory);
		
		assertNotNull(event.findPlayers());
	}
	
	public void testFindPlayersIgnoreState(){
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertNotNull(event.findPlayersIgnoreState());
		event.setSessionFactory(factory);
		
		assertNotNull(event.findPlayersIgnoreState());
	}
	

	public void testFindPlayer(){		
		Integer id = findByPlayerId(testplayerA.getName());
		Player pla = event.findPlayer(id);
		assertNotNull(pla);
		assertEquals("Foo Foo", pla.getName());
		
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertNull(event.findPlayer(id));
		event.setSessionFactory(factory);
		assertNotNull(event.findPlayer(id));

	}
	
	public void testFindScore(){
		Integer s = event.findScore(0);
		assertEquals(new Integer(0), s);
		
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertNull(event.findScore(0));
		event.setSessionFactory(factory);
		assertNotNull(event.findScore(0));
		
	}
	
	public void testFindGames(){
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertNotNull(event.findGames());
		event.setSessionFactory(factory);
		assertNotNull(event.findGames());
	}


	public void testFindGamesOrderedByDate(){
		ArrayList<Games> games = (ArrayList<Games>) event.findGamesOrderedByDate();
		for (int i = 0; i < games.size()-1; i++){
			if(games.get(i).getGameDate().after(games.get(i+1).getGameDate())){
				fail("games not in order by date in testFindGamesOrderedByDate in EventServiceTest");
			}
			if(games.get(i).getGameDate().compareTo(games.get(i+1).getGameDate()) == 0 &&
				games.get(i).getGameNumber() > games.get(i+1).getGameNumber()){
				fail("games with same date are not in order by game number in testFindGamesOrderedByDate in EventServiceTest");
			}
		}
	}
	public void testSaveGame(){
		Games mockGame = new Games();
		mockGame.setGameDate(new Date(3000,1,1));
		mockGame.setId(999999998);
		mockGame.setHost(findByPlayerId(testplayerA.getName()));
		mockGame.setGameScoreId(999999998);
		mockGame.setGameNumber(999999998);
		assertEquals(true, event.saveGame(mockGame));
		// delete mockGame from db
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (Games g : event.findGames()){
			if (g.getGameNumber().equals(999999998)){
				session.delete(g);
			}
		}
		session.getTransaction().commit();
		session.close();
		assertEquals(false, event.saveGame(null));
		
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertEquals(false, event.saveGame(mockGame));
		event.setSessionFactory(factory);

	}

	public void testSaveScore(){
		Score mockScore = new Score();
		mockScore.setId(999999997);
		mockScore.setRank(999999997);
		mockScore.setGameScoreId(999999997);
		mockScore.setPlayerId(findByPlayerId(testplayerA.getName()));
		assertEquals(true, event.saveScore(mockScore));
		assertEquals(false, event.saveScore(null));
		// delete mockScore from db
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (Score s : event.findScores()){
			if (s.getRank().equals(999999997)){
				session.delete(s);
			}
		}
		session.getTransaction().commit();
		session.close();
		
				// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertEquals(false, event.saveScore(mockScore));
		event.setSessionFactory(factory);
	}
	
	public void testFindGamesForDate(){
		Date d = findDateByGameNumber(new Integer(999999999));
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertEquals(new Integer(0), event.findGamesForDate(d));
		event.setSessionFactory(factory);
		assertEquals(new Integer(1), event.findGamesForDate(d));
	}
	
	public void testFindScoreForPlayer(){
		assertEquals(new Integer(999999999), event.findScoreForPlayer(findByPlayerId(testplayerA.getName())));
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertEquals(new Integer(0), event.findScoreForPlayer(findByPlayerId(testplayerA.getName())));
		event.setSessionFactory(factory);
		assertEquals(new Integer(999999999), event.findScoreForPlayer(findByPlayerId(testplayerA.getName())));
	}
	
	
	public void testFindScoresWithPlayerIdParameter(){
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertNull(event.findScores(findByPlayerId(testplayerA.getName())));
		event.setSessionFactory(factory);
		assertNotNull(event.findScores(findByPlayerId(testplayerA.getName())));
	}

	public void testFindScores(){
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertNull(event.findScores());
		event.setSessionFactory(factory);
		assertNotNull(event.findScores());
	}

	public void testFindPoints(){
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertNotNull(event.findPoints());
		event.setSessionFactory(factory);
		assertNotNull(event.findPoints());
	}

	public void testFindScoreForGameAndPlayer(){
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertEquals(new Integer(0), event.findScoreForGameAndPlayer(new Integer(999999999), findByPlayerId(testplayerA.getName())));
		assertEquals(new Integer(0), event.findScoreForGameAndPlayer(new Integer(0), findByPlayerId(testplayerA.getName())));
		event.setSessionFactory(factory);
		assertEquals(new Integer(999999999), event.findScoreForGameAndPlayer(new Integer(999999999), findByPlayerId(testplayerA.getName())));
		assertEquals(new Integer(0), event.findScoreForGameAndPlayer(new Integer(0), findByPlayerId(testplayerA.getName())));
	}

	
	
	public void testSavePlayer(){
		Player mock = new Player();
		mock.setId(999999999);
		mock.setName("Test Save Player");
		mock.setState('D'); // testing that you cannot create unactivated players
		assertTrue(event.savePlayer(mock));

		//cleanup
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(mock);
		session.beginTransaction().commit();
		session.close();
		session = null; 
		
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertFalse(event.savePlayer(mock)); 
		event.setSessionFactory(factory);
	}
	
	public void testDeletePlayerRowFromDatabase(){
		Player mock = new Player();
		mock.setId(999999991);
		mock.setName("test delete player row from database");
		mock.setState('D'); 
		event.savePlayer(mock);
		assertTrue(event.deletePlayerRowFromDatabase(mock));
		for (Player p : event.findPlayers()){
			if (p.getName().compareTo(mock.getName()) == 0){
				Session s = HibernateUtil.getSessionFactory().openSession();
				s.beginTransaction();
				s.delete(mock);
				s.beginTransaction().commit();
				s.close();
				s = null;
				fail("deletePlayerRowFromDatabase should've deleted player but it didn't...");
			}
		}
		
		event.savePlayer(mock);
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertFalse(event.deletePlayerRowFromDatabase(mock));
		event.setSessionFactory(factory);
		// cleanup
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(mock);
		s.beginTransaction().commit();
		s.close();
		s = null;
	}
	
	public void testDeletePlayer(){
		Player mock = new Player();
		mock.setId(999999992);
		mock.setName("test delete player");
		mock.setState('N');
		event.savePlayer(mock);
		assertTrue(event.deletePlayer(mock));
		for (Player p : event.findPlayers()){
			if (p.getName().compareTo(mock.getName()) == 0){
				if(p.getState() != 'D'){
					fail("deletePlayer should've deleted player but it didn't...");
				}
			}
		}
		assertTrue(event.deletePlayerRowFromDatabase(mock)); //cleanup
		
		event.savePlayer(mock);
		// check that exceptions are handled
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertFalse(event.deletePlayer(mock));
		event.setSessionFactory(factory);
		assertTrue(event.deletePlayerRowFromDatabase(mock)); //cleanup
	}
	
	public void testReactivatePlayer(){
		Player mock = new Player();
		mock.setId(999999989);
		mock.setName("test reactivate player");
		mock.setState('N');
		event.savePlayer(mock);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("update Player set state = 'D' where name='"+mock.getName()+"'");
		if(q.executeUpdate() == 0){
			throw new IllegalStateException("testReactivePlayer failed");
		} 
		session.beginTransaction().commit();
		session.close();
		session = null;
		
		assertTrue(event.reactivatePlayer(mock));
		
		// check that exceptions are handled
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		Query q2 = session1.createQuery("update Player set state = 'D' where name='"+mock.getName()+"'");
		if(q2.executeUpdate() == 0){
			throw new IllegalStateException("testReactivePlayer failed");
		}
		session1.beginTransaction().commit();
		session1.close();
		session1 = null;
		
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		assertFalse(event.reactivatePlayer(mock));
		event.setSessionFactory(factory);
		
		
		assertTrue(event.deletePlayerRowFromDatabase(mock));
	}
	
	public void testFindPlayersWithDeletedState(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("update Player set state = 'D' where name='"+testplayerA.getName()+"'");
		if(q.executeUpdate() == 0){
			throw new IllegalStateException("testReactivePlayer failed");
		}
		session.beginTransaction().commit();
		session.close();
		session = null;
		boolean flag = false;
		for (Player p : event.findPlayersWithDeletedState()){
			if (p.getName().compareTo(testplayerA.getName()) == 0){
				flag = true;
			}
		}
		assertTrue(flag);
		
		factory = event.getSessionFactory(); // save real SessionFactory
		event.setSessionFactory(new MockSessionFactory());
		flag = false;
		for (Player p : event.findPlayersWithDeletedState()){
			if (p.getName().compareTo(testplayerA.getName()) == 0){
				flag = true;
			}
		}
		assertTrue(flag);
		event.setSessionFactory(factory);
		
	}
	
	/*
	 * Helper methods
	 */	
	private Integer findByPlayerId(String playerName){
		List<Player> p = event.findPlayers();
		assertNotNull(p);
		Integer id = null;
		for (Player pl : p){
			if (pl.getName().compareTo(playerName) == 0){
				id = pl.getId();
				break;
			}
		}
		assertNotNull(id);
		return id;
	}

	private Date findDateByGameNumber(Integer gameNumber){
		List<Games> games = event.findGames();
		assertNotNull(games);
		Date d = null;
		for (Games g : games){
			if (g.getGameNumber().equals(gameNumber)){
				d = g.getGameDate();
				break;
			}
		}
		assertNotNull(d);
		return d;
	}
	private class MockSessionFactory implements SessionFactory {

		public void close() throws HibernateException {
		}

		public void evict(Class arg0) throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public void evict(Class arg0, Serializable arg1)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public void evictCollection(String arg0) throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public void evictCollection(String arg0, Serializable arg1)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public void evictEntity(String arg0) throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public void evictEntity(String arg0, Serializable arg1)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public void evictQueries() throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public void evictQueries(String arg0) throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
			
		}

		public Map getAllClassMetadata() throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public Map getAllCollectionMetadata() throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public ClassMetadata getClassMetadata(Class arg0)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public ClassMetadata getClassMetadata(String arg0)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public CollectionMetadata getCollectionMetadata(String arg0)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public org.hibernate.classic.Session getCurrentSession()
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public Set getDefinedFilterNames() {
			return null;
		}

		public FilterDefinition getFilterDefinition(String arg0)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public Statistics getStatistics() {
			return null;
		}

		public boolean isClosed() {
			return true;
		}

		public org.hibernate.classic.Session openSession()
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public org.hibernate.classic.Session openSession(Connection arg0) {
			return null;
		}

		public org.hibernate.classic.Session openSession(Interceptor arg0)
				throws HibernateException {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public org.hibernate.classic.Session openSession(Connection arg0,
				Interceptor arg1) {
			throw new HibernateException("MockEventService just threws exceptions");
		}

		public StatelessSession openStatelessSession() {
			return null;
		}

		public StatelessSession openStatelessSession(Connection arg0) {
			return null;
		}

		public Reference getReference() throws NamingException {
			throw new NamingException("MockEventService just threws exceptions");
		}	
		
	}
}
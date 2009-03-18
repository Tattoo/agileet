package pokkare.tests;
import junit.framework.TestCase;

import pokkare.service.EventService;
import pokkare.service.HibernateUtil;
import pokkare.model.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;

public class EventServiceTest extends TestCase {

	private EventService event;
	private Session session;
	private Games testgame;
	private Player testplayerA;
	private Score testscore;
	private Points testpoints;
	
	protected void setUp() throws Exception {
		event = new EventService();

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
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (Score s : event.findScores()){
			if (s.getGameScoreId().equals(999999999)){
				session.delete(s);
			}
		}
		for (Points p : event.findPoints()){
			if (p.getPoints().equals(999999999) && p.getRank().equals(999999999)){
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

	public void testFindPlayers(){
		assertNotNull(event.findPlayers());
	}

	public void testFindPlayer(){
		Integer id = findByPlayerId(testplayerA.getName());
		Player pla = event.findPlayer(id);
		assertNotNull(pla);
		assertEquals("Foo Foo", pla.getName());
	}

	public void testFindScore(){
		Integer s = event.findScore(0);
		assertEquals(new Integer(0), s);
	}

	public void testFindGames(){
		assertNotNull(event.findGames());
	}


	public void testFindGamesForDate(){
		Date d = findDateByGameNumber(new Integer(999999999));
		assertEquals(new Integer(1), event.findGamesForDate(d));
	}

	public void testFindScores(){
		assertNotNull(event.findScores());
	}

	public void testFindPoints(){
		assertNotNull(event.findPoints());
	}

	public void testFindScoreForPlayer(){
		assertEquals(new Integer(999999999), event.findScoreForPlayer(findByPlayerId(testplayerA.getName())));
	}

	public void testFindScoresByPlayerId(){
		List<Score> s = event.findScores(findByPlayerId(testplayerA.getName()));
		assertNotNull(s);
		assertEquals(1, s.size());
		assertEquals(new Integer(999999999), s.get(0).getRank());
	}
	
	public void testFindScoreForGameAndPlayer(){
		assertEquals(new Integer(999999999), event.findScoreForGameAndPlayer(new Integer(999999999), findByPlayerId(testplayerA.getName())));
		assertEquals(new Integer(0), event.findScoreForGameAndPlayer(new Integer(0), findByPlayerId(testplayerA.getName())));
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
}
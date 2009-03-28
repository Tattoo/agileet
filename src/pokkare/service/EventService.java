package pokkare.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.model.Points;
import pokkare.model.Score;

public class EventService {
	
	private static List<Player> playerList;
	private static List<Games> gamesList;
	private static List<Score> scoreList;
	private static List<Points> pointsList;
	
	public List<Player> findPlayers() {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Player");
			List<Player> lista = (List<Player>)query.list();
		 
			playerList = new ArrayList<Player>();
			for (int i = 0; i < lista.size(); ++i) {
				playerList.add(((Player)lista.get(i)));
			}
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return playerList;
	}
	
	public Player findPlayer(Integer id) {
		Session session = null;
		Player player = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			player = (Player)session.get(Player.class, id);
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
			
		
		return player;
	}
	
	public Integer findScore(Integer rank) {
		Session session = null;
		Points point = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			point = (Points)session.get(Points.class, rank);
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
			
		
		return point.getPoints();
	}
	
	
	public List<Games> findGames() {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Games");
			List<Games> lista = (List<Games>)query.list();
		 
			gamesList = new ArrayList<Games>();
			for (int i = 0; i < lista.size(); ++i) {
				gamesList.add(((Games)lista.get(i)));
			}
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return gamesList;
	}
	
	public boolean saveGame(Games game) {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(game);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); return false; }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return true;
	}

	public boolean saveScore(Score score) {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(score);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); return false; }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return true;
	}
	
	public Integer findGamesForDate(Date date) {
		Session session = null;
		Long count = 0L;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String queryDate = "" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("select count(*) from Games where game_date = '" + queryDate + "'");
			ArrayList<Long> lista = (ArrayList<Long>)query.list();
			count = lista.get(0);
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		//System.out.println("asff " + queryDate + " " + count.intValue());
		return count.intValue();
	}
	
	
	
	public Integer findScoreForPlayer(Integer playerId) {
		Session session = null;
		Long sum = 0L;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("select sum(p.points) from Score s, Points p where s.rank = p.rank and player_id = " + playerId);
			ArrayList<Long> lista = (ArrayList<Long>)query.list();
			sum = lista.get(0);
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		return sum.intValue();
	}
	
	
	public List<Score> findScores(Integer playerId) {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Score where player_id=" + playerId);
			List<Score> lista = (List<Score>)query.list();
			
			//System.out.println(lista.size() + " debuggia" );
		 
			scoreList = new ArrayList<Score>();
			for (int i = 0; i < lista.size(); ++i) {
				scoreList.add(((Score)lista.get(i)));
			}
			
			//System.out.println(scoreList.size() + " debuggia2" );
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return scoreList;
	}
	
	public List<Score> findScores() {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Score");
			List<Score> lista = (List<Score>)query.list();
			
			//System.out.println(lista.size() + " debuggia" );
		 
			scoreList = new ArrayList<Score>();
			for (int i = 0; i < lista.size(); ++i) {
				scoreList.add(((Score)lista.get(i)));
			}
			
			//System.out.println(scoreList.size() + " debuggia2" );
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return scoreList;
	}
	
	public List<Points> findPoints() {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Points");
			List<Points> lista = (List<Points>)query.list();
	 
			pointsList = new ArrayList<Points>();
			for (int i = 0; i < lista.size(); ++i) {
				pointsList.add(((Points)lista.get(i)));
			}
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return pointsList;
	}
	
	public Integer findScoreForGameAndPlayer(Integer gameId, Integer playerId) {
		Session session = null;
		Integer i = 0;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Score where game_score_id = " + gameId + " AND player_id = " + playerId);
			ArrayList<Score> lista = (ArrayList<Score>)query.list();
			
			Score score = new Score();
			
			if (lista.size() > 0) {
				score = lista.get(0);
			}
			else { 
				score.setRank(0);
			}
			
			Integer rank = score.getRank();
			i = new EventService().findScore(rank);
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (session != null && session.isOpen()) session.close(); }
		//System.out.println("asff " + queryDate + " " + count.intValue());
		return i;
	}
	
	public boolean savePlayer(Player player) {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(player);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); return false; }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return true;
	}

	public boolean deletePlayer(Player player) {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("update Player set state = 'D' where id = " + player.getId());
			query.executeUpdate();
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); return false; }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return true;
	}
	
	public boolean deletePlayerRowFromDatabase(Player player) {
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(player);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); return false; }
		finally { if (session != null && session.isOpen()) session.close(); }
		
		return true;
	}
	
}


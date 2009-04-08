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
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
	private Session session;
	
	//finds players whose state is not 'D'
	public List<Player> findPlayers() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Player");
			List<Player> lista = (List<Player>)query.list();
		 
			playerList = new ArrayList<Player>();
			for (int i = 0; i < lista.size(); ++i) {
				Player p = (Player)lista.get(i);
				if (p.getState() != 'D'){
					playerList.add(p);
				}
			}
			
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()) {
				session.close(); 
			}
		}
		session = null;
		return playerList;
	}
	
	public List<Player> findPlayersIgnoreState() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Player");
			List<Player> lista = (List<Player>)query.list();
		 
			playerList = new ArrayList<Player>();
			for (int i = 0; i < lista.size(); ++i) {
				Player p = (Player)lista.get(i);
				playerList.add(p);
			}
			
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()) {
				session.close(); 
			}
		}
		session = null;
		return playerList;
	}
	
	public Player findPlayer(Integer id) {
		Player player = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			player = (Player)session.get(Player.class, id);
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()){ 
				session.close(); 
			}
		
		}
			
		session = null;
		return player;
	}
	
	public Integer findScore(Integer rank) {
		Points point = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			point = (Points)session.get(Points.class, rank);
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()){ 
				session.close(); 
			}
		}
			
		session = null;
		return point.getPoints();
	}
	
	
	public List<Games> findGames() {
		
		try {
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
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()){ 
				session.close(); 
			}
		}
		session = null;
		return gamesList;
	}
	
	public List<Games> findGamesOrderedByDate() {
		
		//HQL order by doesn't work so let's hack it
		List<Games> gamesList = findGames();
		
		//really ugly, but I'm not getting paid for this, so...
		for (int i = 0; i < gamesList.size(); ++i) {
			Games gI = gamesList.get(i);
			for (int j = i; j < gamesList.size(); ++j) {
				Games gJ = gamesList.get(j);
				
				if (gJ.getGameDate().getTime() < gI.getGameDate().getTime()) {
					gI = gamesList.remove(i);
					gamesList.add(i, gJ);
					gJ = gamesList.remove(j);
					gamesList.add(j, gI);
				}
			}
		}
		
		for (int i = 0; i < gamesList.size(); ++i) {
			Games gI = gamesList.get(i);
			for (int j = i; j < gamesList.size(); ++j) {
				Games gJ = gamesList.get(j);
				if (gJ.getGameDate().getYear() == gI.getGameDate().getYear() &&
						gJ.getGameDate().getMonth() == gI.getGameDate().getMonth() &&
						gJ.getGameDate().getDay() == gI.getGameDate().getDay()) {
					if (gJ.getGameNumber() < gI.getGameNumber()) {
						gI = gamesList.remove(i);
						gamesList.add(i, gJ);
						gJ = gamesList.remove(j);
						gamesList.add(j, gI);
						System.out.println("bar");
					}
				}
			}
		}
		
		return gamesList;
	}
	
	public boolean saveGame(Games game) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(game);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		}
		finally { 
			if (session != null && session.isOpen()){ session.close(); 
			}
		}
		session = null;
		return true;
	}

	public boolean saveScore(Score score) {
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(score);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		}
		finally { 
			if (session != null && session.isOpen()) {
				session.close(); 				
			}
		}
		session = null;
		return true;
	}
	
	public Integer findGamesForDate(Date date) {
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
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()){
				session.close(); 
			}
		}
		session = null;
		return count.intValue();
	}
	
	
	
	public Integer findScoreForPlayer(Integer playerId) {
		Long sum = 0L;
		try {
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
	
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Score where player_id=" + playerId);
			List<Score> lista = (List<Score>)query.list();
			
			scoreList = new ArrayList<Score>();
			for (int i = 0; i < lista.size(); ++i) {
				scoreList.add(((Score)lista.get(i)));
			}
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { 
			if (session != null && session.isOpen()) {
				session.close(); 
			}
		}
		
		return scoreList;
	}
	
	public List<Score> findScores() {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Score");
			List<Score> lista = (List<Score>)query.list();
			
			scoreList = new ArrayList<Score>();
			for (int i = 0; i < lista.size(); ++i) {
				scoreList.add(((Score)lista.get(i)));
			}
			
			session.close(); 
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { 
			if (session != null && session.isOpen()) { 
				session.close(); 
			}
		}
		session = null;
		return scoreList;
	}
	
	public List<Points> findPoints() {
		
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
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()){ 
				session.close(); 
			}
		}
		session = null;
		return pointsList;
	}
	
	public Integer findScoreForGameAndPlayer(Integer gameId, Integer playerId) {
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
		finally { 
			if (session != null && session.isOpen()){ 
				session.close(); 
			}
		}
		session = null;
		return i;
	}
	
	public boolean savePlayer(Player player) {
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			player.setState('N');
			session.save(player);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		}
		finally { 
			if (session != null && session.isOpen()){ 
				session.close();
			}
		}
		session = null;
		return true;
	}

	public boolean deletePlayer(Player player) {
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("update Player set state = 'D' where id = " + player.getId());
			query.executeUpdate();
			session.getTransaction().commit();
			session.beginTransaction();
			query = session.createQuery("delete from Score where player_id="+player.getId());
			query.executeUpdate();
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		}
		finally { 
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		session = null;
		return true;
	}
	
	public boolean reactivatePlayer(Player player) {
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("woot?");
			Query query = session.createQuery("update Player set state = 'N' where id = " + player.getId());
			int changed = query.executeUpdate();
			session.getTransaction().commit();
			session.beginTransaction();
			session.close(); 
			if (changed != 1) {
				return false;
			}
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		}
		finally { 
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		session = null;
		return true;
	}
	
	public List<Player> findPlayersWithDeletedState() {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Player where state = 'D'");
			List<Player> lista = (List<Player>)query.list();
	 
			playerList = new ArrayList<Player>();
			for (int i = 0; i < lista.size(); ++i) {
				playerList.add(((Player)lista.get(i)));
			}
			
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally { 
			if (session != null && session.isOpen()){ 
				session.close(); 
			}
		}
		session = null;
		return playerList;
	}
	
	//use deletePlayer(Player) if you want to change player state to 'D' instead of this
	//this really removes player data from database
	public boolean deletePlayerRowFromDatabase(Player player) {
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(player);
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		}
		finally { 
			if (session != null && session.isOpen()){ 
				session.close(); 
			}
		}
		session = null;
		return true;
	}
	
}


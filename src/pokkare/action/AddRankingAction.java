package pokkare.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

public class AddRankingAction extends ActionSupport implements ParameterAware {

	private HashMap<Integer, String> gamesMap = new HashMap<Integer, String>();
	private ArrayList<Games> gamesList = new ArrayList<Games>();
	EventService event = new EventService();
	private static Integer chosenGame = -1;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private Map parameters;
	private Integer playerListSize;

	public Integer getPlayerListSize(){
		return this.playerListSize;
	}
	
	public void setPlayerListSize(Integer playerListSize){
		this.playerListSize = playerListSize;
	}
	
	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setChosenGame(Integer chosenGame) {
		this.chosenGame = chosenGame;
	}

	public static Integer getChosenGame() {
		return chosenGame;
	}


	public void setGamesMap(HashMap<Integer, String> gamesMap) {
		this.gamesMap = gamesMap;
	}

	public HashMap<Integer, String> getGamesMap() {
		return gamesMap;
	}
	
	public ArrayList<Games> getGamesList() {
		return gamesList;
	}

	public void setGamesList(ArrayList<Games> gamesList) {
		this.gamesList = gamesList;
	}

	public AddRankingAction() {

	}

	public String execute() {
		playerList = (ArrayList<Player>)event.findPlayers();
		setPlayerListSize(playerList.size());
		Set keyset = null;
		if (parameters != null && parameters.keySet() != null) {
			keyset = parameters.keySet();
		}
		
		if ((chosenGame == null || chosenGame < 0) && (keyset == null || keyset.size() == 0)) {

			ArrayList<Games> a = (ArrayList<Games>)event.findGamesOrderedByDate();
			gamesList = a;
			
//			for (int i = 0; i < a.size(); ++i) {
//				gamesMap.put(a.get(i).getId(), a.get(i).getGameDate() + " nro: " + a.get(i).getGameNumber());
//			}
			return "addranking";
		}
		
		Iterator it = keyset.iterator();
		
		boolean gameChosen = false;
		
		for(int i = 0; i < keyset.size(); ++i) {
			String key = (String)it.next();

			//some game is chosen
			if (key.equals("chosenGame")) {
				gameChosen = true;
			}
		}

		//some game and at least one player chosen
		System.out.println("DEBUG: " + keyset.size());
		if (keyset.size() < 2 || !gameChosen) {
			addActionError(ErrorMessages.ADDRANKING_NOT_ENOUGH_PARAMETERS);
			repopulateGamesList();
			return "error";
		}
		
		//t�ss� luupataan l�pi parameters-mappi josta haetaan pelaaja, 
		//pelaajaID ja tallennetaan score jokaiselle mapin pelaajalle

		int errors = 0;
		
		for (int i = 0; i < playerList.size(); ++i) {

			Player player = (Player)playerList.get(i);
			String playerName = player.getName();
			Integer playerId = player.getId();
			
			if (parameters.containsKey(playerName)) {

				Integer playerRank = -1;

				try { 
					playerRank = Integer.parseInt(((String[])parameters.get(playerName))[0]);
				} catch (Exception e) { 
					addActionError(ErrorMessages.INTERNAL_ERROR);
					e.printStackTrace(); 
					return "error";
				}

				Score score = new Score();
				score.setGameScoreId(chosenGame);
				score.setPlayerId(playerId);
				score.setRank(playerRank);

				System.out.println("saving score");
				if (!event.saveScore(score)) {
					errors++;
					addActionError("Virhe lis�tess� pisteit� pelaajalle: " + event.findPlayer(playerId).getName() + ". Pelaajalle on jo asetettu pisteet valitsemallesi pelille.");
				}
			}
		}
		
		if (errors > 0) {
			int noproblem = (keyset.size() - 1 - errors);
			if (noproblem > 0) 
				addActionMessage("Pisteet onnistuneesti lis�tty " + noproblem + ":lle pelaajalle.");
			repopulateGamesList();
			return "error";
		}

		repopulateGamesList();
		
		chosenGame = -1;
		addActionMessage(ActionMessages.RANKING_ADDED);
		return "success";

	}

	private boolean repopulateGamesList() {
		//repopulate games list so it's still populated when doing "success" 
		ArrayList<Games> a = (ArrayList<Games>)event.findGamesOrderedByDate();
		gamesList = a;
		return true;
	}
	
}


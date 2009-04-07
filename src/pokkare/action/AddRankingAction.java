package pokkare.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.EventService;

public class AddRankingAction implements ParameterAware {

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
		if (chosenGame == null || chosenGame < 0) {

			ArrayList<Games> a = (ArrayList<Games>)event.findGamesOrderedByDate();
			gamesList = a;
			
//			for (int i = 0; i < a.size(); ++i) {
//				gamesMap.put(a.get(i).getId(), a.get(i).getGameDate() + " nro: " + a.get(i).getGameNumber());
//			}
			return "success";
		}

		//t�ss� luupataan l�pi parameters-mappi josta haetaan pelaaja, 
		//pelaajaID ja tallennetaan score jokaiselle mapin pelaajalle

		for (int i = 0; i < playerList.size(); ++i) {

			Player player = (Player)playerList.get(i);
			String playerName = player.getName();
			Integer playerId =player.getId();

			if (parameters.containsKey(playerName)) {

				Integer playerRank = -1;

				try { 
					playerRank = Integer.parseInt(((String[])parameters.get(playerName))[0]);
				} catch (Exception e) { 
					e.printStackTrace(); 
					throw new IllegalStateException("Convert mismatch"); 
				}

				Score score = new Score();
				score.setGameScoreId(chosenGame);
				score.setPlayerId(playerId);
				score.setRank(playerRank);

				System.out.println("saving score");
				event.saveScore(score);
			}
		}


		//System.out.println(parameters.size() + " llss " + ((String[])parameters.get("Jones"))[0]);
		chosenGame = -1;
		return "index";

	}

}

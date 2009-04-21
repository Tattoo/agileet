package pokkare.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ParameterAware;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

import com.opensymphony.xwork2.ActionSupport;


public class EditRankingAction extends ActionSupport implements ParameterAware {
	private Map parameters;
	private static Integer chosenGame = -1;
	private static String chosenGameDesc = "";
	private static List<Games> games; 
	private static Map<String,Integer> playerScores;
	EventService event = new EventService();
	
	
	public static Integer getChosenGame() {
		return chosenGame;
	}

	public static void setChosenGame(Integer chosenGame) {
		EditRankingAction.chosenGame = chosenGame;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public static String getChosenGameDesc() {
		return chosenGameDesc;
	}

	public static void setChosenGameDesc(String chosenGameDesc) {
		EditRankingAction.chosenGameDesc = chosenGameDesc;
	}

	public static List<Games> getGames() {
		return games;
	}

	public static void setGames(List<Games> games) {
		EditRankingAction.games = games;
	}

	public static Map<String, Integer> getPlayerScores() {
		return playerScores;
	}

	public static void setPlayerScores(Map<String, Integer> playerScores) {
		EditRankingAction.playerScores = playerScores;
	}
	
	public String execute() {
		Set keyset = parameters.keySet();
		
		//no game chosen yet => get list of games
		if ((chosenGame == null || chosenGame < 0) && keyset.size() == 0) {
			games = event.findGames();
			return "pickgame";
		}
		
		//game chosen, no players and/or scores chosen yet => get list of players
		if ((chosenGame != null && chosenGame > -1) && keyset.size() == 1) {

			if (!setChosenGameDesc()) {
				addActionError(ErrorMessages.INTERNAL_ERROR);
				return "error";
			}

			populatePlayersList();

			addActionMessage(ActionMessages.GAME_CHOSEN);
			return "editscores";
		}
		
		//game chosen, players and scores chosen, let's rock
		if ((chosenGame != null && chosenGame > -1) && keyset.size() > 1) {
			List<Player> players = event.findPlayers();
			int errors = 0;
			
			for (int i = 0; i < players.size(); ++i) {

				Player player = (Player)players.get(i);
				String playerName = player.getName();
				Integer playerId = player.getId();
				
				if (parameters.containsKey(playerName)) {

					Integer playerRank = -1;

					try { 
						playerRank = Integer.parseInt(((String[])parameters.get(playerName))[0]);
					} catch (Exception e) { 
						addActionError(ErrorMessages.INTERNAL_ERROR);
						e.printStackTrace();
						populatePlayersList();
						setChosenGameDesc();
						return "error";
					}

					Score score = new Score();
					score.setGameScoreId(chosenGame);
					score.setPlayerId(playerId);
					score.setRank(playerRank);
					
					System.out.println("deleting old score");
					if (!event.deleteScore(chosenGame, playerId)) {
						//well if not successful then who cares, maybe there wasn't one
					}

					System.out.println("saving new score");
					if (!event.saveScore(score)) {
						errors++;
						addActionError("Virhe muuttaessa pelaajan " + event.findPlayer(playerId).getName() + " pisteitä.");
					}
				}
			}
			
			if (errors > 0) {
				setChosenGameDesc();
				populatePlayersList();
				return "error";
			}
			
			//repop
			chosenGame = -1;
			games = event.findGames();
			return "success";
		}
		
		//never returned
		return "success";
	}

	//find players, populate players and scores list
	private void populatePlayersList() {
		List<Player> players = event.findPlayers();
		for (int i = 0; i < players.size(); ++i) {
			Player p = (Player)players.get(i);
			playerScores.put(p.getName(), event.findScoreForGameAndPlayer(chosenGame, p.getId()));
		}
	}
	
	private boolean setChosenGameDesc() {
		//check if such a game exists
		Games game = event.findGame(chosenGame);
		if (game == null) {
			games = event.findGames();
			return false;
		}
		
		setChosenGameDesc(game.getGameDate() + " #" + game.getGameNumber());
		return true;
	}
	

}
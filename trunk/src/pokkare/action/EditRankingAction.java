package pokkare.action;

import java.util.HashMap;
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
		if ((chosenGame == null || chosenGame < 0) || keyset.size() == 0) { 
			playerScores = null; // reset
			games = event.findGames();
			return "pickgame";
		}

		//check that we're not in wrong if
		List<Player> players = event.findPlayers();
		int found = 0;
		
		for (int i = 0; i < players.size(); ++i) {

			Player player = (Player)players.get(i);
			String playerName = player.getName();
			Integer playerId = player.getId();
			
			if (parameters.containsKey(playerName)) {
				found++;
				break;
			}
		}
		
		//game chosen, no players and/or scores chosen yet => get list of players
		if ((chosenGame != null && chosenGame > -1) && keyset.size() == 1 && found == 0) {
			
			if (!setChosenGameDesc()) {
				addActionError(ErrorMessages.INTERNAL_ERROR);
				return "error";
			}

			populatePlayersList();

			addActionMessage(ActionMessages.GAME_CHOSEN);
			//chosenGame = -1; // reset chosenGame, provide chosenGame again from UI
			
			//nullify games, print only chosen game
			games = null;
			return "editscores";
		}
		
		otherif:
		//game chosen, player(s) and scores chosen, let's rock
		if ((chosenGame != null && chosenGame > -1) && found != 0) {
			//reset found to be used in this context
			found = 0;
			int errors = 0;
			
			for (int i = 0; i < players.size(); ++i) {

				Player player = (Player)players.get(i);
				String playerName = player.getName();
				Integer playerId = player.getId();
				
				if (parameters.containsKey(playerName)) {
					found++;
					Integer playerRank = -1;

					try { 
						playerRank = Integer.parseInt(((String[])parameters.get(playerName))[0]);
					} catch (Exception e) { 
						addActionError(ErrorMessages.INTERNAL_ERROR);
						e.printStackTrace();
						populatePlayersList();
						setChosenGameDesc();
						chosenGame = -1;
						games = event.findGames();
						playerScores = null;
						return "error";
					}

					Score score = new Score();
					score.setGameScoreId(chosenGame);
					score.setPlayerId(playerId);
					score.setRank(playerRank);
					
					System.out.println("deleting old score");
					if (!event.deleteScore(chosenGame, playerId)) {
						//well if not successful then who cares, maybe there wasn't one
						System.out.println("Virhe poistaessa scorea pelaajalle: " + playerId + " pelille " + chosenGame);
					}

					//if rank is -1 it's set to be removed, don't save -1 score
					if (playerRank != -1) {
						System.out.println("saving new score");
						if (!event.saveScore(score)) {
							errors++;
							addActionError("Virhe muuttaessa pelaajan " + event.findPlayer(playerId).getName() + " pisteit&auml;.");
						}
					}
				}
			}
			
			if (errors > 0 || found == 0) {
				setChosenGameDesc();
				chosenGame = -1;
				games = event.findGames();
				playerScores = null;
				populatePlayersList();
				return "error";
			}
			
			//repop
			chosenGame = -1;
			games = event.findGames();
			playerScores = null;
			addActionMessage("Tallennus onnistui.");
			//nullify chosen game
			chosenGame = null;
			return "success";
		}
		
		//never returned
		return "success";
	}

	//find players, populate players and scores list
	private void populatePlayersList() {
		playerScores = new HashMap<String, Integer>();
		for(Player p : event.findPlayers()){
			Integer s = event.findScoreForGameAndPlayer(chosenGame, p.getId());
			if (s == null){
				s = 0;
			}
			playerScores.put(p.getName(), s);	
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
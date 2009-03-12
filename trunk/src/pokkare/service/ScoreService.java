package pokkare.service;

import java.util.ArrayList;
import java.util.HashMap;

import pokkare.model.Player;
import pokkare.model.Score;

public class ScoreService {
	
	public static final HashMap<Integer, Integer> playerScores = new HashMap<Integer, Integer>();
	public static final EventService event = new EventService();
	public static final PointsService points = new PointsService();
	
	/** do this only once */
	static {
		ArrayList<Score> scores = (ArrayList<Score>)event.findScores();
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();
		
		/** laita kumulatiivinen summa mappiin */
		for (int i = 0; i < players.size(); ++i) {
			
		}
	}
	
	public HashMap<Integer, Integer> getPlayerScores() {
		return playerScores;
	}

	
	
}
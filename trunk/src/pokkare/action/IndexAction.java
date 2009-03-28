package pokkare.action;

import java.util.ArrayList;

import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.EventService;

public class IndexAction {
	
	private EventService event = new EventService();
	private ArrayList<String> ranking = new ArrayList<String>();
	
	public IndexAction() {
		
	}
	
	public ArrayList<String> getRanking() {
		return ranking;
	}
	
	public void setRanking(ArrayList<String> ranking) {
		this.ranking = ranking;
	}
	
	
	public String execute() {
		
		ArrayList<Player> playerList = (ArrayList<Player>)event.findPlayers();
		
		if (playerList == null) {
			throw new IllegalStateException("No players available.");
		}
		
		Integer[] scoreTable = new Integer[playerList.size()];
		String[] nameTable = new String[playerList.size()];
		
		for (int i = 0; i < playerList.size(); ++i) {
			Player p = playerList.get(i);
			
			//skip deleted players
			if (p.getState() == 'D') {
				continue;
			}
			
			Integer playerId = p.getId();
			String playerName = p.getName();
			Integer cumulativeScore = 0;
			
			ArrayList<Score> scores = (ArrayList<Score>)event.findScores(playerId);

			for (int j = 0; j < scores.size(); ++j) {
				Score score = scores.get(j);
				Integer rank = score.getRank();
				cumulativeScore = cumulativeScore + event.findScore(rank);
			}
			
			//ranking.put(cumulativeScore, playerName);
			scoreTable[i] = cumulativeScore;
			nameTable[i] = playerName;
		}
		
		//sorttaa nimet
		for (int i = 0; i < scoreTable.length; ++i) {
			for (int j = i + 1; j < scoreTable.length; ++j) {
				if (scoreTable[i] < scoreTable[j]) {
					Integer k = scoreTable[i];
					scoreTable[i] = scoreTable[j];
					scoreTable[j] = k;
					String s = nameTable[i];
					nameTable[i] = nameTable[j];
					nameTable[j] = s;
				}
			}
		}
		
		for (int i = 0; i < scoreTable.length; ++i) {
			System.out.println(scoreTable[i] + nameTable[i]);
			ranking.add(nameTable[i] + ": " + scoreTable[i]);
		}
		
		
		return "success";
	}
	
}
package pokkare.action;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.EventService;
import pokkare.service.PokkareGraphDrawer;
import pokkare.service.ScoreDataWrapper;
import pokkare.service.ScoreDataWrapper.ScoreData;

public class ViewRankingAction  {

	private EventService event = new EventService();
	private ArrayList<String> ranking = new ArrayList<String>();
	private String size;
	Integer picSize = 0;
	HashMap<String, Integer> scores;

	public HashMap<String, Integer> getScores(){
		return this.scores;
	}

	public void setScores(HashMap<String, Integer> scores){
		this.scores = scores;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public ViewRankingAction() {

	}

	public ArrayList<String> getRanking() {
		return ranking;
	}

	public void setRanking(ArrayList<String> ranking) {
		this.ranking = ranking;
	}


	public String execute() {

		ArrayList<Player> playerList = (ArrayList<Player>)event.findPlayers();

		Integer[] scoreTable = new Integer[playerList.size()];
		String[] nameTable = new String[playerList.size()];

		for (int i = 0; i < playerList.size(); ++i) {
			Integer playerId = playerList.get(i).getId();
			String playerName = playerList.get(i).getName();
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

		int maxPoints = scoreTable[0];

		HashMap<String, Integer> scoresTable = new HashMap<String, Integer>();
		for (int i = 0; i < playerList.size(); i++){
			scoresTable.put(nameTable[i], scoreTable[i]);
		}
		setScores(scoresTable);


		drawPokkareGraph(maxPoints);

		return "success";

	}

	public void drawPokkareGraph(int maxPoints) {

		try {  
			String WEBAPP_ROOT = org.apache.struts2.ServletActionContext.getServletContext().getRealPath("/");

			FileOutputStream f = new FileOutputStream(WEBAPP_ROOT + "/pokkaregraph.jpg");

			PokkareGraphDrawer drawer = new PokkareGraphDrawer();

			ScoreDataWrapper dataWrapper = new ScoreDataWrapper();

			
			drawer.createGraphs(dataWrapper, dataWrapper.getMaxScore(), dataWrapper.getNumberOfGames());
			
			if (size != null) {
				if (size.equals("plus"))
					drawer.setMultiplier(4);
				if (size.equals("minus"))
					drawer.setMultiplier(2);
			}
			else drawer.setMultiplier(3);
			drawer.setMaxPoints(maxPoints);
			drawer.createImage(f); 
			f.flush();
			f.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
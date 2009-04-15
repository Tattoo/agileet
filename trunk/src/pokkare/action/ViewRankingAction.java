package pokkare.action;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.EventService;
import pokkare.service.ScoreDataWrapper;
import pokkare.service.ScoreDataWrapper.ScoreData;

public class ViewRankingAction  {

	private EventService event = new EventService();
	private ArrayList<String> ranking = new ArrayList<String>();
	private String size;
	Integer picSize = 0;
	HashMap<String, HashMap<Integer, Integer>> scores;
	HashMap<Integer, String> games;
	ArrayList<GameDataObject> gamesList;
	ScoreDataWrapper scoreDataWrapper = new ScoreDataWrapper();
	List<ScoreData> scoreDatas = scoreDataWrapper.getScoreDatas();
	
	public List<ScoreData> getScoreDatas() {
		return scoreDatas;
	}

	public void setScoreDatas(List<ScoreData> scoreDatas) {
		this.scoreDatas = scoreDatas;
	}

	public ScoreDataWrapper getScoreDataWrapper() {
		return scoreDataWrapper;
	}

	public void setScoreDataWrapper(ScoreDataWrapper scoreDataWrapper) {
		this.scoreDataWrapper = scoreDataWrapper;
	}

	public HashMap<String, HashMap<Integer, Integer>> getScores(){
		return this.scores;
	}

	public void setScores(HashMap<String, HashMap<Integer, Integer>> scores){
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

	public HashMap<Integer, String> getGames() {
		return games;
	}

	public void setGames(HashMap<Integer, String> games) {
		this.games = games;
	}

	public ArrayList<GameDataObject> getGamesList() {
		return gamesList;
	}

	public void setGamesList(ArrayList<GameDataObject> gamesList) {
		this.gamesList = gamesList;
	}
	
	public String execute() {
		HashMap<String, HashMap<Integer, Integer>> data = new HashMap<String, HashMap<Integer, Integer>>();
		HashMap<Integer, String> games = new HashMap<Integer, String>();
		gamesList = new ArrayList<GameDataObject>();
		for (ScoreData sd : getScoreDatas()){
			String name = sd.getPlayer().getName();
			Integer score = sd.getScore();
			Integer positionInSeries = sd.getPositionInSeries();
			Games game = sd.getGame();
			if(data.containsKey(name)){
				data.get(name).put(positionInSeries, score);
			}
			else {
				HashMap<Integer, Integer> scoreList = new HashMap<Integer, Integer>();
				scoreList.put(0,0);
				scoreList.put(positionInSeries, score);
				data.put(name, scoreList);
			}
			if (!games.containsKey(positionInSeries)) {
				GameDataObject g = new GameDataObject(positionInSeries, game.getGameDate(), game.getGameNumber());
				String s = positionInSeries + ": " + game.getGameDate() + " #" + game.getGameNumber();
				gamesList.add(g);
				System.out.println(s);
				games.put(positionInSeries, s);
			}
		}

		setScores(data);
		setGames(games);

		int imageHeight = scoreDataWrapper.getMaxScore() * 10;
		int minSize = 400;
		int maxSize = 650;
		if (imageHeight < minSize)
			imageHeight = minSize;
		if (imageHeight > maxSize)
			imageHeight = maxSize;
		
		int imageWidth = imageHeight;
		
		/* TODO: refactor image width and height smarter than this
		Map session = ActionContext.getContext().getSession();
		session.put("image_height", imageHeight);
		session.put("image_width", imageWidth);
		*/
		
		return "success";

	}

	public class GameDataObject {
		private int positionInSeries;
		private Date gameDate;
		private int gameNumber;
		
		public GameDataObject(int positionInSeries, Date gameDate, int gameNumber) {
			this.positionInSeries = positionInSeries;
			this.gameDate = gameDate;
			this.gameNumber = gameNumber;
		}
		
		public int getPositionInSeries() {
			return positionInSeries;
		}
		public void setPositionInSeries(int positionInSeries) {
			this.positionInSeries = positionInSeries;
		}
		public Date getGameDate() {
			return gameDate;
		}
		public void setGameDate(Date gameDate) {
			this.gameDate = gameDate;
		}
		public int getGameNumber() {
			return gameNumber;
		}
		public void setGameNumber(int gameNumber) {
			this.gameNumber = gameNumber;
		}

		
	}

}
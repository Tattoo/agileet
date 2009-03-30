package pokkare.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import pokkare.model.Games;
import pokkare.model.Player;

/*
 * Wrapper class for multiple score data objects.
 */

public class ScoreDataWrapper {
	
	List<ScoreData> scoreDatas = new ArrayList<ScoreData>();
	int maxScore;
	int numberOfGames;
	
	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public void setScoreDatas(List<ScoreData> scoreDatas) {
		this.scoreDatas = scoreDatas;
	}

	public List<ScoreData> getScoreDatas() {
		return scoreDatas;
	}
	
	public ScoreDataWrapper() {
		ScoreData scoreData = null;
		
		EventService event = new EventService();
		
		//get games and players
		ArrayList<Games> games = (ArrayList<Games>)event.findGames();
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();

		//auxiliary map to aid in keeping score of players' total scores
		//<player id, player score>
		Map<Integer, Integer> playerScores = new HashMap<Integer, Integer>();

		Integer maxScore = 0;
		
		//loop players
		for (int i = 0; i < players.size(); ++i) {
			
			Player player = players.get(i);
			
			//do not include deleted players
			if (player.getState() == 'D') {
				continue;
			}
			
			//get score datas list
			List<ScoreData> scoreDatas = this.getScoreDatas();
			
			//loop games
			for (int j = 0; j < games.size(); ++j) {
				Games game = games.get(j);
				//get scores
				Integer score = event.findScoreForGameAndPlayer(game.getId(), player.getId());
				//find and update cumulative score for this player
				if (playerScores.containsKey(player.getId())) {
					score = score + playerScores.get(player.getId()).intValue();
					playerScores.remove(player.getId());
					playerScores.put(player.getId(), score);
				}
				//if none, initialize for this player
				else {
					playerScores.put(player.getId(), score);
				}

				if (score > maxScore) {
					maxScore = score;
				}
				
				//add new data object
				scoreData = this.new ScoreData(player, game, score.intValue(), j + 1, games.size());
				scoreDatas.add(scoreData);
			}
		}
		
		this.setMaxScore(maxScore);
		this.setNumberOfGames(games.size());
		
	}
	

	/*
	 * Data object of score data. Includes date of game, number of game and score fields. 
	 * Score is the cumulative, or actual score to be printed. This means that the player's
	 * first data object should have score 10, second one score 16 if player scored 10 from
	 * the first game and 6 from the second game.
	 */
	
	public class ScoreData {
		private Player player;	
		private Games game;
		private int score;

		//running number of the score's position in the series (for this player)
		private int positionInSeries;
		private boolean firstInSeries;
		private boolean lastInSeries;
		
		public ScoreData(Player player, Games game, int score, int positionInSeries, int maxPosition) {
			setPlayer(player);
			setGame(game);
			setScore(score);
			setPositionInSeries(positionInSeries);
			setFirstInSeries(positionInSeries == 1 ? true : false);
			setLastInSeries(positionInSeries == maxPosition ? true : false);
			
			System.out.println("DE DA DUG DE DUM: " + this.toString());
		}

		public Games getGame() {
			return game;
		}

		public void setGame(Games game) {
			this.game = game;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		public boolean getFirstInSeries() {
			return firstInSeries;
		}

		public void setFirstInSeries(boolean firstInSeries) {
			this.firstInSeries = firstInSeries;
		}

		public int getPositionInSeries() {
			return positionInSeries;
		}

		public void setPositionInSeries(int positionInSeries) {
			this.positionInSeries = positionInSeries;
		}

		public boolean getLastInSeries() {
			return lastInSeries;
		}

		public void setLastInSeries(boolean lastInSeries) {
			this.lastInSeries = lastInSeries;
		}
		
		public String toString() {
			return this.getPositionInSeries() + " " + this.getFirstInSeries() + " " + this.getLastInSeries() + " " + this.player.getName() + " " + this.getGame().getGameDate() + " #" + this.getGame().getGameNumber() + " " + this.getScore() + " ";
		}
	}
	
}
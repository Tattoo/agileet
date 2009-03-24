package pokkare.service;

import java.util.ArrayList;
import java.util.List;


import pokkare.model.Games;
import pokkare.model.Player;

/*
 * Wrapper class for multiple score data objects.
 */

public class ScoreDataWrapper {
	
	List<ScoreData> scoreDatas = new ArrayList<ScoreData>();
	
	public List<ScoreData> getScoreDatas() {
		return scoreDatas;
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
		
		public ScoreData(Player player, Games game, int score) {
			setPlayer(player);
			setGame(game);
			setScore(score);
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
		

	}
	
}
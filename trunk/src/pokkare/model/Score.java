package pokkare.model;

import java.io.Serializable;

/**
 * Persistenssiluokka, sis‰lt‰‰ Hibernaten tarvitsemat get-
 * ja set-metodit sek‰ tarvittavat muuttujat GAMES
 * -taulun tietokantaoperaatioita varten.
 */

public class Score implements Serializable {
	
	private static final long serialVersionUID = -1862654962199448166L;
	
	private Integer id;
	private Integer gameScoreId;
	private Integer playerId;
	private Integer rank;	

	

	
	public Integer getId() {
		return id;	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getGameScoreId() {
		return gameScoreId;	
	}
	public void setGameScoreId(Integer gameScoreId) {
		this.gameScoreId = gameScoreId;
	}
	
	
	public Integer getPlayerId() {
		return playerId;	
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	
	
	
	public Integer getRank() {
		return rank;	
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	



}
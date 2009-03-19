package pokkare.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Persistenssiluokka, sis‰lt‰‰ Hibernaten tarvitsemat get-
 * ja set-metodit sek‰ tarvittavat muuttujat GAMES
 * -taulun tietokantaoperaatioita varten.
 */

public class Games implements Serializable {
	
	private static final long serialVersionUID = -1862654962199448166L;
	
	private Integer id;
	private String description;
	private Integer host;
	private Date gameDate;
	private Integer gameNumber;
	private Integer gameScoreId;

	

	
	public Integer getId() {
		return id;	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getHost() {
		return host;
	}
	public void setHost(Integer host) {
		this.host = host;
	}
	
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	
	public Integer getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(Integer gameNumber) {
		this.gameNumber = gameNumber;
	}
	
	public Integer getGameScoreId() {
		return gameScoreId;
	}
	public void setGameScoreId(Integer gameScoreId) {
		this.gameScoreId = gameScoreId;
	}
	

}
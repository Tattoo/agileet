package pokkare.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Persistenssiluokka, sis‰lt‰‰ Hibernaten tarvitsemat get-
 * ja set-metodit sek‰ tarvittavat muuttujat GAMES
 * -taulun tietokantaoperaatioita varten.
 */

public class Points implements Serializable {
	
	private static final long serialVersionUID = -1862654962199448166L;
	
	private Integer rank;
	private Integer points;
	
	public Integer getRank() {
		return rank;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}

	
	
}
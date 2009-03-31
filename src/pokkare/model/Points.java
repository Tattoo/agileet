package pokkare.model;

import java.io.Serializable;


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
package pokkare.service;

import java.util.ArrayList;
import java.util.HashMap;

import pokkare.model.Points;


public class PointsService {
	
	public static final HashMap<Integer, Integer> scores = new HashMap<Integer, Integer>();
	public static final EventService event = new EventService();
	
	static {
		ArrayList<Points> a = (ArrayList<Points>)event.findPoints();
		for (int i = 0; i < a.size(); ++i) {
			scores.put(a.get(i).getRank(), a.get(i).getPoints());
		}
	}
	
	
	public Integer getScore(Integer rank) {
		return scores.get(rank);
	}
	
}
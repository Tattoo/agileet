package pokkare.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import pokkare.model.Player;
import pokkare.service.EventService;

public class ReactivatePlayerAction implements ParameterAware {
	private Map parameters;
	private EventService event = new EventService();
	
	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public String execute() {
		String deletePlayerName = ((String[])parameters.get("delete_player_name"))[0];
		Player reactivatePlayer = getReactivatePlayerByPlayerName(deletePlayerName);
		
		if (reactivatePlayer != null) {
			event.reactivatePlayer(reactivatePlayer);
			return "success";
		}
		
		return "player_not_found";
	}
	
	//find and return player by player name
	public Player getReactivatePlayerByPlayerName(String playerName) {
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();
		
		for (int i = 0; i < players.size(); ++i) {
			Player p = players.get(i);
			if (p.getName().equals(playerName)) {
				return p;
			}
		}
		return null;
	}
	
	
}
package pokkare.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import pokkare.model.Player;
import pokkare.service.EventService;

public class DeletePlayerAction implements ParameterAware {
	private Map parameters;
	EventService event = new EventService();
	
	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public String execute() {
		String deletePlayerName = ((String[])parameters.get("delete_player_name"))[0];
		Player deletePlayer = getDeletablePlayerByPlayerName(deletePlayerName);
		
		if (deletePlayer != null) {
			event.deletePlayer(deletePlayer);
			return "success";
		}
		
		return "player_not_found";
	}
	
	public boolean deletePlayerRowFromDatabase(Player player) {
		return event.deletePlayerRowFromDatabase(player);
	}
	
	public Player getDeletablePlayerByPlayerName(String deletePlayerName) {
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();
		
		for (int i = 0; i < players.size(); ++i) {
			Player deletePlayer = players.get(i);
			if (deletePlayer.getName().equals(deletePlayerName)) {
				return deletePlayer;
			}
		}
		return null;
	}
}
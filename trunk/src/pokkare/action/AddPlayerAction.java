package pokkare.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import pokkare.model.Player;
import pokkare.model.Score;
import pokkare.service.EventService;

public class AddPlayerAction implements ParameterAware {
	private Map parameters;
	EventService event = new EventService();
	
	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public String execute() {
		Player addPlayer = new Player();
		String addPlayerName = ((String[])parameters.get("add_player_name"))[0];
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();
		
		for (int i = 0; i < players.size(); ++i) {
			if (players.get(i).getName().equals(addPlayerName)) {
				return "name_already_exists";
			}
		}
		
		addPlayer.setName(addPlayerName);
		event.savePlayer(addPlayer);
		
		return "success";
	}

}
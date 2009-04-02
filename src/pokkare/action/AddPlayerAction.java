package pokkare.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import pokkare.model.Player;
import pokkare.service.EventService;

public class AddPlayerAction implements ParameterAware {
	private Map parameters;
	ArrayList<String> players = new ArrayList<String>();
	private EventService event = new EventService();

	public ArrayList<String> getPlayers(){
		return players;
	}
	
	public void setPlayers(ArrayList<String> players){
		this.players = players;
	}
	
	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public String execute() {
		Player addPlayer = new Player();
		String addPlayerName = null;
		for (Player p : (ArrayList<Player>)event.findPlayers()){
			players.add(p.getName());
		}

		try {
			addPlayerName = ((String[])parameters.get("add_player_name"))[0];
		} catch (NullPointerException npe) {
			return "manage";
		}
		if (addPlayerName.compareTo("") == 0){
			// TODO: refactor this to return error instead of blowing stuff up with exceptions
			// TODO: then, update the test case too!!
			throw new IllegalArgumentException("add_player_name in parameters was empty string [in AddPlayerAction.execute()]");
		}
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();
		for (Player p : players){
			if (p.getName().compareTo(addPlayerName) == 0){
				return "name_already_exists";
			}
		}
		addPlayer.setName(addPlayerName);
		event.savePlayer(addPlayer);
		
		return "success";
	}

}
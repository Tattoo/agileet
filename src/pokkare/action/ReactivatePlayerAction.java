package pokkare.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

import pokkare.model.Player;
import pokkare.service.EventService;

public class ReactivatePlayerAction extends ActionSupport implements ParameterAware {
	private Map parameters;
	private EventService event = new EventService();
	ArrayList<String> players = new ArrayList<String>();
	ArrayList<String> stateDPlayers = new ArrayList<String>();
	
	
	public ArrayList<String> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

	public ArrayList<String> getStateDPlayers() {
		return stateDPlayers;
	}

	public void setStateDPlayers(ArrayList<String> stateDPlayers) {
		this.stateDPlayers = stateDPlayers;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public String execute() {
		String deletePlayerName = ((String[])parameters.get("reactivate_player_name"))[0];
		Player reactivatePlayer = getReactivatePlayerByPlayerName(deletePlayerName);
		
		//this gets a list of active player's names for deletion
		for (Player p : (ArrayList<Player>)event.findPlayers()){
			players.add(p.getName());
		}
		//this gets a list of status 'D' players for reactivation
		for (Player p: (ArrayList<Player>)event.findPlayersWithDeletedState()) {
			stateDPlayers.add(p.getName());
		}
		
		if (reactivatePlayer != null) {
			if (event.reactivatePlayer(reactivatePlayer)) {
				addActionMessage("Pelaaja aktivoitu.");
				return "success";
			}
		}
		
		addActionError("Virhe: tämän nimistä pelaajaa ei löytynyt.");
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
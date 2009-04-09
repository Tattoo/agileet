package pokkare.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

import pokkare.model.Player;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
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
		setUpData();
		String deletePlayerName = ((String[])parameters.get("reactivate_player_name"))[0];
		Player reactivatePlayer = getReactivatePlayerByPlayerName(deletePlayerName);
		if (reactivatePlayer != null) {
			if (event.reactivatePlayer(reactivatePlayer)) {
				setUpData();
				addActionMessage(ActionMessages.PLAYER_REACTIVATED);
				return "success";
			}
		}
		
		addActionError(ErrorMessages.PLAYER_BY_THIS_NAME_NOT_FOUND);
		return "player_not_found";
	}
	
	//find and return player by player name
	public Player getReactivatePlayerByPlayerName(String playerName) {
		for (Player p : event.findPlayersWithDeletedState()){
			if (p.getName().compareTo(playerName) == 0){
				return p;
			}
		}
		return null;
	}
	// setup data for the jsp
	private void setUpData(){
		players = new ArrayList<String>(); // reset the variable
		stateDPlayers = new ArrayList<String>(); // reset the variable
		
		//this gets a list of active player's names for deletion
		for (Player p : (ArrayList<Player>)event.findPlayers()){
			players.add(p.getName());
		}
		//this gets a list of status 'D' players for reactivation
		for (Player p: (ArrayList<Player>)event.findPlayersWithDeletedState()) {
			stateDPlayers.add(p.getName());
		}		
	}
	
	
}
package pokkare.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

import pokkare.model.Player;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

public class DeletePlayerAction extends ActionSupport implements ParameterAware {
	private Map parameters;
	EventService event = new EventService();
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
		setupData();
		
		String[] temp = (String[])parameters.get("delete_player_name");
		if (temp == null){
			addActionError(ErrorMessages.NO_PLAYER_IN_REQUEST);
			return "error";
		}
		String deletePlayerName = temp[0];
		Player deletePlayer = getDeletablePlayerByPlayerName(deletePlayerName);
		
		if (deletePlayer != null) {
			event.deletePlayer(deletePlayer);
			addActionMessage(ActionMessages.PLAYER_DELETED);
			setupData();
			return "success";
		}
		
		addActionError(ErrorMessages.PLAYER_BY_THIS_NAME_NOT_FOUND);
		return "player_not_found";
	}
	
	//used only in test class
	public boolean deletePlayerRowFromDatabase(Player player) {
		return event.deletePlayerRowFromDatabase(player);
	}
	
	public Player getDeletablePlayerByPlayerName(String deletePlayerName) {
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayersIgnoreState();
		
		for (int i = 0; i < players.size(); ++i) {
			Player deletePlayer = players.get(i);
			if (deletePlayer.getName().equals(deletePlayerName)) {
				return deletePlayer;
			}
		}
		return null;
	}
	
	private void setupData(){
		// cleanup before usage
		players = null;
		players = new ArrayList<String>();
		stateDPlayers = null;
		stateDPlayers = new ArrayList<String>();
		
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
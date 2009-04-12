package pokkare.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.service.ActionMessages;
import pokkare.service.ErrorMessages;
import pokkare.service.EventService;

public class AddEventAction extends ActionSupport {
	
	private EventService event = new EventService();
	private HashMap<Integer, String> hosts = new HashMap<Integer, String>();
	private String desc;
	private String time;
	public SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yyyy");
	private Integer host;
	private Games game;

	public EventService getEvent(){
		return this.event;
	}
	public void setEvent(EventService event){
		this.event = event;
	}
	
	public HashMap<Integer, String> getHosts() {
		List<Player> l = event.findPlayers();
		
		for (int i = 0; i < l.size(); ++i) {
			hosts.put(((Player)(l.get(i))).getId(), ((Player)(l.get(i))).getName());
		}
		
		return hosts;
	}
	
	public void setHosts(HashMap<Integer, String> hosts) {
		this.hosts = hosts;
	}
	
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setHost(Integer host) {
		this.host = host;
	}
	
	public Integer getHost() {
		return host;
	}
	
	public String execute() {
		
		System.out.println("time: "+time);
		
		if (time == null) return "addevent";
		
		System.out.println(desc);
		System.out.println(host);
		
		game = new Games();
		game.setDescription(desc);
		Date theDate = null;
		try {
			theDate = dateParser.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(ErrorMessages.DATE_NOT_PROCESSABLE);
			return "error";
		}
		game.setGameDate(theDate);
		game.setGameNumber(event.findGamesForDate(theDate) + 1);
		game.setGameScoreId(0);
		game.setHost(host);
		
		event.saveGame(game);
		
		addActionMessage(ActionMessages.GAME_ADDED);
		return "success";
	}

	

	
	
	
	
}
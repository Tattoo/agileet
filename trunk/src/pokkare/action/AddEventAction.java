package pokkare.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pokkare.model.Games;
import pokkare.model.Player;
import pokkare.service.EventService;

public class AddEventAction {
	
	private EventService event = new EventService();
	private HashMap<Integer, String> hosts = new HashMap<Integer, String>();
	private String desc;
	private Date time;
	private Integer host;
	private Games game;
	
	public AddEventAction() {
		
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
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public Date getTime() {
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
		
		if (time == null) return "success";
		
		System.out.println(desc);
		System.out.println(host);
		
		game = new Games();
		game.setDescription(desc);
		game.setGameDate(time);
		game.setGameNumber(event.findGamesForDate(time) + 1);
		game.setGameScoreId(0);
		game.setHost(host);
		
		event.saveGame(game);
		
		return "index";
	}
	

	
	
	
	
}
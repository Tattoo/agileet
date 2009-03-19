package pokkare.model;

import java.io.Serializable;

/**
 * Persistenssiluokka, sis‰lt‰‰ Hibernaten tarvitsemat get-
 * ja set-metodit sek‰ tarvittavat muuttujat GAMES
 * -taulun tietokantaoperaatioita varten.
 */

public class Player implements Serializable {
	
	private static final long serialVersionUID = -1862654962199448166L;
	
	private Integer id;
	private String name;

	

	
	public Integer getId() {
		return id;	
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	

}
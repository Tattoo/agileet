package pokkare.service;

/* The character sets are different in different platforms.
 * Therefore it is better to keep message texts that are rendered in html anyway as
 * per html convention suggests, eg: 
 * 		"Š" -> "&auml;"
 * 		"š" -> "&ouml;"
 * 		"Œ" -> "&aring;"
 * 		"€" -> "&Auml;"
 * 		"…" -> "&Ouml;"
 * 		"�" -> "&Aring;"
 * 		"<" -> "&gt;"
 * 		">" -> "&lt;"
 *		etc. etc.
 *
 * check more at: http://www.ascii.cl/htmlcodes.htm
 */ 

public class ErrorMessages {
	public final static String PLAYER_BY_THIS_NAME_NOT_FOUND = "T&auml;m&auml;n nimist&auml; pelaajaa ei l&ouml;ytynyt.";
	public final static String DATE_NOT_PROCESSABLE = "Antamaasi p&auml;iv&auml;m&auml;&auml;r&auml;&auml; ei voitu k&auml;sitell&auml;.";
	public final static String DUPLICATE_PLAYER_NAME = "T&auml;m&auml;n niminen pelaaja on jo olemassa.";
	public final static String INTERNAL_ERROR = "Tapahtui sis&auml;inen virhe.";
	public final static String NO_PLAYERS_AVAILABLE = "Tietokannassa ei ole pelaajia.";
	public final static String NO_PLAYER_IN_REQUEST = "Pelaajan nimi hukkui prosessoinnissa joten mit&auml;&auml;n ei tehty.";
	public final static String ADDRANKING_NOT_ENOUGH_PARAMETERS = "Antamasi valinnat olivat puutteelliset.";
}
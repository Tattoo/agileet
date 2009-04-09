package pokkare.service;

/* The character sets are different in different platforms.
 * Therefore it is better to keep message texts that are rendered in html anyway as
 * per html convention suggests, eg: 
 * 		"Š" -> "&auml;"
 * 		"š" -> "&ouml;"
 * 		"Œ" -> "&aring;"
 * 		"€" -> "&Auml;"
 * 		"…" -> "&Ouml;"
 * 		"" -> "&Aring;"
 * 		"<" -> "&gt;"
 * 		">" -> "&lt;"
 *		etc. etc.
 *
 * check more at: http://www.ascii.cl/htmlcodes.htm
 */ 
public class ActionMessages {
	public static final String PLAYER_REACTIVATED = "Pelaaja aktivoitu.";
	public static final String GAME_ADDED = "Peli lis&auml;tty.";
	public static final String PLAYER_ADDED = "Pelaaja lis&auml;tty.";
	public static final String RANKING_ADDED = "Ranking lis&auml;tty.";
	public static final String PLAYER_DELETED = "Pelaaja poistettu.";
}
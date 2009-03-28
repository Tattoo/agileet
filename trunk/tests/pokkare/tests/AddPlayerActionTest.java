package pokkare.tests;
import junit.framework.TestCase;
import pokkare.action.AddPlayerAction;
import pokkare.model.Player;
import pokkare.service.EventService;
import pokkare.service.HibernateUtil;

import java.util.HashMap;

import org.hibernate.Session;

public class AddPlayerActionTest extends TestCase {

	private AddPlayerAction action;

	protected void setUp() throws Exception {
		action = new AddPlayerAction();
	}
	
	protected void tearDown() throws Exception {
		action = null;
	}
	
	public void testParametersAccessors(){
		assertNull(action.getParameters());

		HashMap<String, String[]> param = new HashMap<String, String[]>();
		String[] value = {"bar"};
		String key = "foo";
		param.put(key, value);
		
		action.setParameters(param);
		
		assertEquals(1, action.getParameters().size());
		assertTrue(action.getParameters().containsKey(key));
		assertTrue(action.getParameters().containsValue(value));
		assertTrue(action.getParameters().get(key).equals(value));
		assertEquals("bar", ((String[])action.getParameters().get(key))[0]);
		
		action.setParameters(null); // cleanup after
	}
	
	public void testExecute(){
		assertEquals("manage",action.execute());

		HashMap<String, String[]> param = new HashMap<String, String[]>();
		String playerName = "Rafael Tommasi";
		String[] value = {playerName};
		param.put("add_player_name", value);

		action.setParameters(param);

		assertEquals("success", action.execute());
		assertEquals("name_already_exists", action.execute());

		assertTrue(deleteTestData(playerName));

		value[0] = "";
		param.put("add_player_name", value);
		try {
			action.execute();
			fail("execute() should've failed with IllegalArgumentException in AddPlayerActionTest");
		} catch (IllegalArgumentException ex){ 
			// TODO: put the rest of the test stuff here inside the catch
		}

	}
	private boolean deleteTestData(String playerName){
		// delete stuff that was put in db during this test case
		EventService event = new EventService();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			for (Player p : event.findPlayers()){
				if (p.getName().compareTo(playerName) == 0){
					session.delete(p);
				}
			}
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			if (session != null && session.isOpen()){
				session.close();
			}
			return false;
		}
	}
}
package pokkare.tests;

import java.lang.reflect.Field;
import junit.framework.TestCase;
import pokkare.service.ActionMessages;

public class ActionMessagesTest extends TestCase {
	ActionMessages msg;
	
	protected void setUp() throws Exception {
		msg = new ActionMessages();
	}

	protected void tearDown() throws Exception {
		msg = null;
	}
	
	public void testClassIsNotEmpty(){
		Field[] f = msg.getClass().getDeclaredFields();
		assertNotNull(f);
		assertTrue(f.length > 0);
	}
}

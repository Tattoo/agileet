package pokkare.tests;

import java.lang.reflect.Field;
import junit.framework.TestCase;
import pokkare.service.ErrorMessages;

public class ErrorMessagesTest extends TestCase {
	ErrorMessages msg;
	protected void setUp() throws Exception {
		msg = new ErrorMessages();
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

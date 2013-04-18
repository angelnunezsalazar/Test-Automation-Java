package diapositivas.unittest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SetUpTearDown {

	private User user;

	@Before
	public void setUp(){
		user=createUser("user","pass");
	}

	@Test
	public void transferTest(){
		boolean result=login("user","pass");
		assertTrue(result);
	}

	@After
	public void tearDown(){
		removeUser(user);
	}

	private User createUser(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean login(String string, String string2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void removeUser(User user2) {
		// TODO Auto-generated method stub
		
	}
}

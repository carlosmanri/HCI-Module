package logic.userTest;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.ApplicationException;
import logic.user.UserAccount;

public class UserAccountTest {

	@Test
	public void validUserAccount() throws ApplicationException {
		UserAccount user = new UserAccount("username", "password");
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());
	}
	
	@Test(expected = ApplicationException.class)
	public void emptyUsernameTest() throws ApplicationException {
		new UserAccount("","asdf");
	}
	@Test(expected = ApplicationException.class)
	public void nullUsernameTest() throws ApplicationException {
		new UserAccount(null,"asdf");
	}
	
	@Test(expected = ApplicationException.class)
	public void emptyPasswordTest() throws ApplicationException {
		new UserAccount("asdf","");
	}
	@Test(expected = ApplicationException.class)
	public void nullPasswordTest() throws ApplicationException {
		new UserAccount("asdf",null);
	}
	
	@Test
	public void serializeTest() throws ApplicationException {		
		UserAccount user = new UserAccount("test", "test");		
		assertEquals("User test@test", user.serialize());
	}

	@Test
	public void equalsTest() throws ApplicationException {
		UserAccount user = new UserAccount("test", "test");		
		UserAccount user2 = new UserAccount("test", "test");		
		assertEquals(user, user2);		
	}
	
	@Test
	public void notEqualsTest() throws ApplicationException {
		UserAccount user = new UserAccount("test", "test");		
		UserAccount user2 = new UserAccount("otherTest", "otherTest");		
		assertNotEquals(user, user2);		
	}
	
	
}

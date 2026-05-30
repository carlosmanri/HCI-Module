package logic.parserTest;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.ApplicationException;
import logic.user.UserAccount;

public class UserSerializerTest {

	@Test
	public void userSerializerTest() throws ApplicationException {
		
		UserAccount user = new UserAccount("username", "password");
		String result = "User username@password";
		assertEquals(result, user.serialize());
	}

}

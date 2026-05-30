package logic.parserTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import logic.ApplicationException;
import logic.parser.UserAccountParser;
import logic.user.UserAccount;

public class UserParserTest {

	List<UserAccount> users;

	@Before
	public void Setup() throws ApplicationException {
		users = new ArrayList<UserAccount>();
		
		users.add(new UserAccount("carlos", "carlos"));
		users.add(new UserAccount("test1", "test1"));
		users.add(new UserAccount("test2", "test2"));
		users.add(new UserAccount("test3", "test3"));

		
	}

	@Test
	public void ValidTest() throws ApplicationException {
		List<String> lines;
		try {
			lines = new logic.file.FileUtil().loadLines("testFiles/clientes.dat");
		} catch (FileNotFoundException e) {
			throw new ApplicationException("The file does not exists", e);
		}
		List<UserAccount> u = new UserAccountParser().parse(lines);
		
		

		//assertArrayEquals(users.toArray(), u.toArray());
		assertEquals(users, u);
	}

	@Test 
	public void InvalidTest() throws ApplicationException {
		List<String> lines;
		try {
			lines = new logic.file.FileUtil().loadLines("testFiles/clientesWrong.dat");
		} catch (FileNotFoundException e) {
			throw new ApplicationException("The file does not exists", e);
		}
		List<UserAccount> u = new UserAccountParser().parse(lines);
		
		users = new ArrayList<UserAccount>();		
		users.add(new UserAccount("admin", "admin"));
		
		assertEquals(users, u);

	}

}

package logic.parser;

import java.util.ArrayList;
import java.util.List;

import logic.ApplicationException;
import logic.user.UserAccount;

public class UserAccountParser {

	private static final int USER_PARTS = 2;
	private static final String LINE_SEPARATOR = "@";


	public List<UserAccount> parse(List<String> lines) {
		List<UserAccount> res = new ArrayList<>();

		for (String line : lines) {
			if (line.length() == 0)
				continue; // If blank line ignore it
			try {
				UserAccount v = parseLine(line);
				res.add(v);
			} catch (InvalidLineException e) {
				System.err.println("ParserError:" + e.getMessage());
			}
		}
		return res;
	}

	private UserAccount parseLine(String line) throws InvalidLineException {
		if(!line.startsWith("User "))
			throw new InvalidLineException("Wrong line format");
		line = line.replace("User ", "");
		String[] parts = line.split(LINE_SEPARATOR);

		return createUserAccount(parts);

	}


	private UserAccount createUserAccount(String[] parts) throws InvalidLineException {
		checkFieldsNumber(USER_PARTS, parts);

		String user = parts[0];
		String password = parts[1];

		try {
			return new UserAccount(user, password);
		} catch (ApplicationException e) {
			throw new InvalidLineException(e.getMessage());
		}
	}


	private void checkFieldsNumber(int size, String[] parts) throws InvalidLineException {
		if (parts.length == size)
			return;
		throw new InvalidLineException("Wrong number of fields. ");
	}

}

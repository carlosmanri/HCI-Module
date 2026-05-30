package logic.parser;

import java.util.LinkedList;
import java.util.List;

import logic.ApplicationException;
import logic.user.UserAccount;

public class UserAccountSerializer {

	public List<String> serialize(List<UserAccount> users) throws ApplicationException {
		List<String> res = new LinkedList<>();
		for(UserAccount u: users) {
			if(u==null) {
				throw new ApplicationException("Error: Tried to serialize a null object. ");
			}
			res.add( serialize( u )  );
		}
		return res;
	}

	private String serialize(UserAccount u) {
		return u.serialize();
	}
}

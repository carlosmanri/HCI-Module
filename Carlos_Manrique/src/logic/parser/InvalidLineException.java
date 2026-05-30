package logic.parser;

public class InvalidLineException extends Exception {

	private static final long serialVersionUID = -8806768759102658097L;

	public InvalidLineException() {
	}

	public InvalidLineException(String arg0) {
		super(arg0);
	}

	public InvalidLineException(Throwable arg0) {
		super(arg0);
	}

	public InvalidLineException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidLineException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}

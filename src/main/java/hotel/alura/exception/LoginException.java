package hotel.alura.exception;

public class LoginException extends RuntimeException {

	public LoginException(String s) {
		super(s);
	}

	public LoginException(String s, Throwable throwable) {
		super(s, throwable);
	}
}

package exceptions;

public class LoginTakenException extends RuntimeException {
    public LoginTakenException() {
        super("Login already taken");
    }
}

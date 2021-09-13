package exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("Token not found");
    }
}

package exceptions;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException() {
        super("Token is expired");
    }
}

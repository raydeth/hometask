package exception;

public class UserAccountNotFoundException extends RuntimeException {
    public UserAccountNotFoundException() {
        super("Can not find user account");
    }
}

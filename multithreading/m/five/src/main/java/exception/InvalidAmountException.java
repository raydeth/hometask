package exception;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException() {
        super("Amount in user account is less then exchange amount");
    }
}

package exception;

public class CurrencyNotFoundException extends RuntimeException{
    public CurrencyNotFoundException() {
        super("Can not find currency");
    }
}

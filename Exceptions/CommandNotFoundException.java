package rus.nsu.fit.oop.lab2.Exceptions;

public class CommandNotFoundException extends Exception {

    public CommandNotFoundException() {
        super();
    }
    public CommandNotFoundException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

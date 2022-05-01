package rus.nsu.fit.oop.lab2.Exceptions;
public class BuildCommandException extends Exception {

    public BuildCommandException() {
        super();
    }
    public BuildCommandException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

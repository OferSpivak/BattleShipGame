package exceptions;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class InitializationFail extends Exception {
    String message = "";

    public InitializationFail(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "Initialization Fail due to: " + message;
    }
}

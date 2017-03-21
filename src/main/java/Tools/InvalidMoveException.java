package Tools;

/**
 * Created by johnhenning on 3/19/17.
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException() {

    }

    public InvalidMoveException(String message) {
        super(message);
    }
}

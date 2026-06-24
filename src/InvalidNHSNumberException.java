/**
 * Custom exception thrown when NHS Number value is invalid.
 */
public class InvalidNHSNumberException extends Exception {
    public InvalidNHSNumberException(String message) {
        super(message);
    }
}

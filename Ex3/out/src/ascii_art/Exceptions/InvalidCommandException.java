package ascii_art.Exceptions;

/**
 * This exception is thrown when an invalid command is encountered while processing ASCII art.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs an InvalidCommandException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}

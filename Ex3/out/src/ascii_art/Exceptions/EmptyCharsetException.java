package ascii_art.Exceptions;

/**
 * This exception is thrown when user try to create an asciiArt .
 */
public class EmptyCharsetException extends Exception {
    private static final String EMPTY_CHARSET_MESSAGE = "Did not execute. Charset is empty.";
    /**
     * Constructs an InvalidCommandException with the specified detail message.
     */
    public EmptyCharsetException() {
        super(EMPTY_CHARSET_MESSAGE);
    }
}

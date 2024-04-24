package ascii_art.Exceptions;

/**
 * This exception is thrown when a user try to reach an invalid resolution fo the current image.
 */
public class InvalidResolutionException extends Exception{
    private static final String RESOLUTION_OUT_OF_BOUNDARIES_MESSAGE = "Did not change resolution due to " +
            "exceeding boundaries.";
        /**
         * Constructs an InvalidResolutionException with the specified detail message.
         */
        public InvalidResolutionException() {
            super(RESOLUTION_OUT_OF_BOUNDARIES_MESSAGE);
        }
    }



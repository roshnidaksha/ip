package planit.exceptions;

/**
 * Thrown to indicate that user has entered invalid arguments.
 */
public class InvalidArgumentException extends Exception {
    /**
     * Constructs an {@code InvalidArgumentException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     * by the {@link Throwable#getMessage()} method).
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}

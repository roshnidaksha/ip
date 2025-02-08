/**
 * Thrown to indicate that a method has been passed an incomplete argument.
 */
public class EmptyCommandException extends Exception {
    /**
     * Constructs an {@code EmptyCommandException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     * by the {@link Throwable#getMessage()} method).
     */
    public EmptyCommandException(String message) {
        super(message);
    }
}

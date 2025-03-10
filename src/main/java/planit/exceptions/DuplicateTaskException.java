package planit.exceptions;

/**
 * Thrown to indicate that a task is already in the task list.
 */
public class DuplicateTaskException extends Exception {
    /**
     * Constructs a {@code DuplicateTaskException} with the specified detail message.
     */
    public DuplicateTaskException() {
        super("This task already exists in the task list.");
    }
}

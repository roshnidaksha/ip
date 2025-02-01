/**
 * Represents tasks without any date/time attached to it.
 * These tasks need to be done some day.
 */
public class Todo extends Task {
    /**
     * Constructs a todo task.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return Todo task represented as a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Represents tasks that need to be done before a specific data/time.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the deadline task.
     * @param deadline Due date of the deadline task.
     */
    public Deadline(String description, String deadline) {
        super.description = description;
        super.isDone = false;
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Deadline task represented as a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}

/**
 * Represents a task in Planit task management system.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Description of task to add.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Task converted to a string.
     */
    @Override
    public String toString() {
        return "[" + (isDone() ? "X" : " ") + "] " + getDescription();
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}

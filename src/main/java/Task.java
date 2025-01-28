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
     * Returns the given task converted into a string.
     *
     * @return Task as a string.
     */
    public String convertToString() {
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

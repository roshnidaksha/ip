package planit.task;

/**
 * Represents a task in Planit task management system.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

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

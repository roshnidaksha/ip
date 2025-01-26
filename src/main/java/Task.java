/**
 * Represents a task in Planit task management system.
 * A task includes a description.
 * <p>
 * This class provides methods to manage and update the task's attributes.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method that converts the given task into a string
     *
     * @return Returns the task as a string
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

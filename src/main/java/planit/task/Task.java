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
    public abstract String toString();

    /**
     * Returns true if the task is equal to another object.
     *
     * @param obj Object to compare with.
     * @return True if the task is equal to the object.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Returns a string representation of the event task that
     * suitable to be stored in file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns a task object from a string.
     *
     * @param input String representation of task stored in file.
     * @return Task object of string representation.
     */
    public static Task parseTask(String input) {
        return null;
    }

    /**
     * Returns the task type in a single letter.
     */
    public abstract String getTaskType();

    public String getStatus() {
        return isDone ? "X" : " ";
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

/**
 * Represents a task in Planit task management system.
 * A task includes a description.
 * <p>
 * This class provides methods to manage and update the task's attributes.
 */
public class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

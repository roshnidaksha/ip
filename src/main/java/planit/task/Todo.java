package planit.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        super.description = description;
        super.isDone = false;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return Todo task represented as a string.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskType(), getStatus(), getDescription());
    }

    /**
     * Returns a string representation of the todo task that
     * suitable to be stored in file storage.
     *
     * @return Todo task represented in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s", getTaskType(), getStatus(), getDescription());
    }

    /**
     * Parses a todo task from string representation.
     *
     * @param input String representation of task stored in file.
     * @return Todo task object of string representation.
     */
    public static Task parseTask(String input) {
        Pattern pattern = Pattern.compile("^T \\| (X| ) \\| (.+?)$");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            return null;
        }

        boolean isDone = matcher.group(2).equals("X");
        String description = matcher.group(3).trim();

        Todo todoTask;
        try {
            todoTask = new Todo(description);
        } catch (Exception e) {
            return null;
        }

        if (isDone) {
            todoTask.setDone(true);
        }

        return todoTask;
    }

    /**
     * Returns the task type in a single letter.
     *
     * @return Type of task.
     */
    @Override
    public String getTaskType() {
        return "T";
    }
}

package planit.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return String.format("[%s][%s] %s (by: %s)", getTaskType(), getStatus(), getDescription(), deadline);
    }

    /**
     * Returns a string representation of the deadline task that
     * suitable to be stored in file storage.
     *
     * @return Deadline task represented in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s", getTaskType(), getStatus(), getDescription(), deadline);
    }

    /**
     * Parses a deadline task from string representation.
     *
     * @param input String representation of task stored in file.
     * @return Deadline task object of string representation.
     */
    public static Task parseTask(String input) {
        Pattern pattern = Pattern.compile("^D \\| (X| ) \\| (.+?) \\| (.+?)$");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            return null;
        }

        boolean isDone = matcher.group(1).equals("X");
        String description = matcher.group(2).trim();
        String deadline = matcher.group(3).trim();

        Deadline deadlineTask;
        try {
            deadlineTask = new Deadline(description, deadline);
        } catch (Exception e) {
            return null;
        }

        if (isDone) {
            deadlineTask.setDone(true);
        }

        return deadlineTask;
    }

    /**
     * Returns the task type in a single letter.
     *
     * @return Type of task.
     */
    @Override
    public String getTaskType() {
        return "D";
    }
}

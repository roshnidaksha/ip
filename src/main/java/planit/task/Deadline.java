package planit.task;

import planit.exceptions.InvalidArgumentException;
import planit.parser.DateParser;
import planit.messages.PlanitExceptionMessages;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents tasks that need to be done before a specific data/time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the deadline task.
     * @param deadline Due date of the deadline task.
     */
    public Deadline(String description, String deadline) throws InvalidArgumentException {
        super.description = description;
        super.isDone = false;
        LocalDateTime parsedDateTime = DateParser.parseDateTime(deadline.trim());
        if (parsedDateTime == null) {
            throw new InvalidArgumentException(String.format(PlanitExceptionMessages.INVALID_DATE_FORMAT, deadline));
        }
        this.deadline = parsedDateTime;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Deadline task represented as a string.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                getTaskType(), getStatus(), getDescription(), DateParser.toFileFormat(deadline));
    }

    /**
     * Returns true if the deadline task is equal to another deadline object.
     *
     * @param obj Object to compare with.
     * @return True if the deadline task is equal to the object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Deadline deadlineTask = (Deadline) obj;
        return description.equals(deadlineTask.getDescription())
                && deadline.equals(deadlineTask.deadline);
    }

    /**
     * Returns a string representation of the deadline task that
     * suitable to be stored in file storage.
     *
     * @return Deadline task represented in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s",
                getTaskType(), getStatus(), getDescription(), DateParser.toFileFormat(deadline));
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

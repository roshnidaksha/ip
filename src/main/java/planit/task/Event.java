package planit.task;

import planit.exceptions.InvalidArgumentException;
import planit.parser.DateParser;
import planit.messages.PlanitExceptionMessages;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents tasks that start a specific date/time and
 * ends at a specific date/time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an event task.
     *
     * @param description Description of the event task.
     * @param from Start date/time of the event task.
     * @param to End date/time of the event task.
     */
    public Event(String description, String from, String to) throws InvalidArgumentException {
        super.description = description;
        super.isDone = false;
        LocalDateTime parsedStart = DateParser.parseDateTime(from);
        LocalDateTime parsedEnd = DateParser.parseDateTime(to);
        if (parsedStart == null) {
            throw new InvalidArgumentException(String.format(PlanitExceptionMessages.INVALID_DATE_FORMAT, from));
        }
        if (parsedEnd == null) {
            throw new InvalidArgumentException(String.format(PlanitExceptionMessages.INVALID_DATE_FORMAT, to));
        }
        if (parsedStart.isAfter(parsedEnd)) {
            throw new InvalidArgumentException(String.format(PlanitExceptionMessages.INVALID_DATE_RANGE, to, from));
        }
        this.start = parsedStart;
        this.end = parsedEnd;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return Event task represented as a string.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                getTaskType(), getStatus(), getDescription(), DateParser.toFileFormat(start), DateParser.toFileFormat(end));
    }

    /**
     * Returns true if the event task is equal to another event object.
     *
     * @param obj Object to compare with.
     * @return True if the event task is equal to the object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Event eventTask = (Event) obj;
        return description.equals(eventTask.getDescription())
                && start.equals(eventTask.start)
                && end.equals(eventTask.end);
    }

    /**
     * Returns a string representation of the event task that
     * suitable to be stored in file storage.
     *
     * @return Event task represented in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s -> %s",
                getTaskType(), getStatus(), getDescription(), DateParser.toFileFormat(start), DateParser.toFileFormat(end));
    }

    /**
     * Parses an event task from string representation.
     *
     * @param input String representation of task stored in file.
     * @return Event task object of string representation.
     */
    public static Task parseTask(String input) {
        Pattern pattern = Pattern.compile("^E \\| (X| ) \\| (.+?) \\| (.+?) -> (.+)$");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            return null;
        }

        boolean isDone = matcher.group(1).equals("X");
        String description = matcher.group(2).trim();
        String start = matcher.group(3).trim();
        String end = matcher.group(4).trim();

        Event eventTask;
        try {
            eventTask = new Event(description, start, end);
        } catch (Exception e) {
            return null;
        }

        if (isDone) {
            eventTask.setDone(true);
        }

        return eventTask;
    }

    /**
     * Returns the task type in a single letter.
     *
     * @return Type of task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }
}

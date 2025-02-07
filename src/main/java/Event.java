/**
 * Represents tasks that start a specific date/time and
 * ends at a specific date/time.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an event task.
     *
     * @param description Description of the event task.
     * @param from Start date/time of the event task.
     * @param to End date/time of the event task.
     */
    public Event(String description, String from, String to) {
        super.description = description;
        super.isDone = false;
        this.start = from;
        this.end = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return Event task represented as a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + ", to: " + end + ")";
    }
}

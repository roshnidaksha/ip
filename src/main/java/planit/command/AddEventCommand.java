package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.task.Event;
import planit.task.TaskList;

/**
 * Handles addition of an event task to list.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_FORMAT = """
            Format: event <task description> /from <task start time> /to <task end time>
            Example: event attend CS2113 lecture /from Friday 4pm /to 6pm
            This will add task [E][ ] attend CS2113 lecture (from: Friday 4pm, to: 6pm)
            """;
    public static final String COMMAND_DESC = "Adds a new event task to your list";
    public static final String[] COMMAND_KEYWORDS = {"description", "/from", "to"};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * Event task requires a description, a start time and an end time.
     *
     * @return True if valid, False otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length &&
                parameters.containsKey(COMMAND_KEYWORDS[0]) &&
                parameters.containsKey(COMMAND_KEYWORDS[1]) &&
                parameters.containsKey(COMMAND_KEYWORDS[2]);
    }

    /**
     * Adds a new event task to list.
     *
     * @param tasks Current list of tasks.
     * @throws InvalidArgumentException If supplied arguments is not valid.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        if (!isValidParameters()) {
            throw new InvalidArgumentException(PlanitExceptionMessages.WRONG_ARGUMENTS);
        }
        String description = parameters.get(COMMAND_KEYWORDS[0]);
        String startTime = parameters.get(COMMAND_KEYWORDS[1]);
        String endTime = parameters.get(COMMAND_KEYWORDS[2]);
        tasks.addTask("event", new Event(description, startTime, endTime));
    }
}


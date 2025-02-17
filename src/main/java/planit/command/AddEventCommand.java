package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.task.Event;
import planit.task.TaskList;

/**
 * Handles addition of an event task to list.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_FORMAT = "event <task description> /from <task start time> /to <task end time>";
    public static final String COMMAND_DESC = "Adds a new event task to your list";
    public static final String[] COMMAND_KEYWORDS = {"description", "/from", "to"};


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
            throw new InvalidArgumentException("Please provide the correct number of arguments.");
        }
        String description = parameters.get(COMMAND_KEYWORDS[0]);
        String startTime = parameters.get(COMMAND_KEYWORDS[1]);
        String endTime = parameters.get(COMMAND_KEYWORDS[2]);
        tasks.addTask("event", new Event(description, startTime, endTime));
    }
}


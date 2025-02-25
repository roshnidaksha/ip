package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.task.TaskList;

/**
 * Displays the list of tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_FORMAT = """
            Format: list
            NOTE: List takes in no arguments
            Example: list""";
    public static final String COMMAND_DESC = "Displays all tasks in the list";
    public static final String[] COMMAND_KEYWORDS = {};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * To display list of all tasks, no arguments is required.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length;
    }

    /**
     * Displays all tasks.
     *
     * @param tasks Current list of tasks.
     * @throws InvalidArgumentException If supplied arguments is not valid.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        if (!isValidParameters()) {
            throw new InvalidArgumentException(PlanitExceptionMessages.WRONG_ARGUMENTS);
        }
        tasks.displayAllTasks();
    }
}

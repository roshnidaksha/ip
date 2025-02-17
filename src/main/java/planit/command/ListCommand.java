package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.task.TaskList;

/**
 * Displays the list of tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_FORMAT = "list";
    public static final String COMMAND_DESC = "Display all tasks in the list";
    public static final String[] COMMAND_KEYWORDS = {};

    /**
     * Checks if supplied arguments are valid.
     * To display list of all tasks, no arguments is required.
     *
     * @return True if valid, False otherwise.
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
            throw new InvalidArgumentException("Please provide the correct number of arguments.");
        }
        tasks.displayAllTasks();
    }
}

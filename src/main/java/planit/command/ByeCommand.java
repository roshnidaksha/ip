package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.task.TaskList;
import planit.util.Ui;

/**
 * Exits planit task management session.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_FORMAT = "bye";
    public static final String COMMAND_DESC = "Exit planit task management session";
    public static final String[] COMMAND_KEYWORDS = {};

    /**
     * Checks if supplied arguments are valid.
     * To exit planit task management session, no arguments is required.
     *
     * @return True if valid, False otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length;
    }

    /**
     * Exits planit task management session.
     *
     * @param tasks Current list of tasks.
     * @throws InvalidArgumentException If supplied arguments is not valid.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        if (!isValidParameters()) {
            throw new InvalidArgumentException("Please provide the correct number of arguments.");
        }
        Ui.showTaskManagerExitMessage();
        isExit = true;
    }
}

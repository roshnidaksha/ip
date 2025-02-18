package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.TaskList;
import planit.util.Ui;

import java.io.IOException;

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
     * Exits planit task management session and saves tasks to file.
     *
     * @param tasks Current list of tasks.
     * @throws InvalidArgumentException If supplied arguments is not valid.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        if (!isValidParameters()) {
            throw new InvalidArgumentException(PlanitExceptionMessages.WRONG_ARGUMENTS);
        }

        try {
            tasks.saveTasks();
            Ui.showToUser(PlanitMessages.TASK_SAVE_SUCCESS);
        } catch (IOException e) {
            Ui.showToUser(PlanitMessages.TASK_SAVE_FAILURE);
            Ui.showError(e.getMessage());
        }

        Ui.showTaskManagerExitMessage();
        isExit = true;
    }
}

package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.TaskList;
import planit.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Exits planit task management session.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_FORMAT = """
            Format: bye
            NOTE: Exit command takes in no arguments
            Example: bye""";
    public static final String COMMAND_DESC = "Exits planit task management session";
    public static final String[] COMMAND_KEYWORDS = {};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * To exit planit task management session, no arguments is required.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
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
    public CommandResult execute(TaskList tasks) throws InvalidArgumentException {
        if (!isValidParameters()) {
            throw new InvalidArgumentException(PlanitExceptionMessages.WRONG_ARGUMENTS);
        }

        ArrayList<String> feedback = new ArrayList<>();

        try {
            tasks.saveTasks();
            feedback.add(PlanitMessages.TASK_SAVE_SUCCESS);
            feedback.add(Ui.FILE_PATH);
        } catch (IOException e) {
            feedback.add(PlanitMessages.TASK_SAVE_FAILURE);
            feedback.add(e.getMessage());
        }

        feedback.add(PlanitMessages.TASK_MANAGER_MESSAGE_GOODBYE);
        isExit = true;

        return new CommandResult(feedback);
    }
}

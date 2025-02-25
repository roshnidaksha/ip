package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.task.TaskList;

/**
 * Represents the command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_FORMAT = """
            Format: find <keyword>
            Example: find cs2113 - finds tasks that contain the keyword 'cs2113'
            """;
    public static final String COMMAND_DESC = "Finds tasks that contain the keyword";
    public static final String[] COMMAND_KEYWORDS = {"description"};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * To delete a task, only the task type and index is required.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length &&
                parameters.containsKey(COMMAND_KEYWORDS[0]);
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param tasks Current list of tasks.
     * @throws InvalidArgumentException If supplied arguments is not valid.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        if (!isValidParameters()) {
            throw new InvalidArgumentException(PlanitExceptionMessages.WRONG_ARGUMENTS);
        }
        String keyword = parameters.get(COMMAND_KEYWORDS[0]);
        tasks.displayTasksWithKeyword(keyword);
    }
}

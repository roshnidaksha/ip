package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.Task;
import planit.task.TaskList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_FORMAT = """
            Format: find <keyword>
            Example: find cs2113 - finds tasks that contain the keyword 'cs2113'""";
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
    public CommandResult execute(TaskList tasks) throws InvalidArgumentException {
        if (!isValidParameters()) {
            throw new InvalidArgumentException(PlanitExceptionMessages.WRONG_ARGUMENTS);
        }

        ArrayList<String> feedback = new ArrayList<>();
        String keyword = parameters.get(COMMAND_KEYWORDS[0]);
        HashMap<String, ArrayList<Task>> tasksWithKeyword = tasks.getTasksWithKeyword(keyword);
        if (tasksWithKeyword.isEmpty()) {
            feedback.add(PlanitMessages.FIND_TASK_FAILURE);
        } else {
            feedback.add(PlanitMessages.LIST_SUCCESS);
        }
        return new CommandResult(feedback, tasksWithKeyword);
    }
}

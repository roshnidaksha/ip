package planit.command;

import planit.exceptions.EmptyCommandException;
import planit.exceptions.InvalidArgumentException;
import planit.handler.Parser;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.TaskList;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_FORMAT = """
            Format: delete <task type><task index>
            NOTE: task type can only be (t, d, e)
            Example: delete td1 - deletes the first deadline task
            """;
    public static final String COMMAND_DESC = "Deletes the specified task";
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
     * Deletes a task.
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
        try {
            String[] result = Parser.validateIndex(description, tasks.taskCount);
            String taskType = result[0];
            int taskIndex = Integer.parseInt(result[1]);
            tasks.deleteTask(taskType, taskIndex - 1);
        } catch (EmptyCommandException e) {
            throw new InvalidArgumentException(String.format(PlanitMessages.DELETE_TASK_FAILURE, e.getMessage()));
        }
    }
}

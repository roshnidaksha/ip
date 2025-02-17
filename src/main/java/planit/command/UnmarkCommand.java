package planit.command;

import planit.exceptions.EmptyCommandException;
import planit.exceptions.InvalidArgumentException;
import planit.handler.Parser;
import planit.task.TaskList;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_FORMAT = """
            unmark <task type><task index>
            NOTE: task type can only be (t, d, e)
            """;
    public static final String COMMAND_DESC = "Mark the specified task as not done";
    public static final String[] COMMAND_KEYWORDS = {"description"};

    /**
     * Checks if supplied arguments are valid.
     * To unmark a task, only the task type and index is required.
     *
     * @return True if valid, False otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length;
    }

    /**
     * Marks a task as done.
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
        try {
            String[] result = Parser.validateIndex(description, tasks.taskCount);
            String taskType = result[0];
            int taskIndex = Integer.parseInt(result[1]);
            tasks.setTaskStatus(taskType, taskIndex - 1, false);
        } catch (EmptyCommandException e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }
}

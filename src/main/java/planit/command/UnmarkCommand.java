package planit.command;

import planit.exceptions.EmptyCommandException;
import planit.exceptions.InvalidArgumentException;
import planit.handler.Parser;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.TaskList;
import planit.util.Ui;

import java.util.ArrayList;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_FORMAT = """
            Format: unmark <task type><task index>
            NOTE: task type can only be (t, d, e)
            Example: unmark td1 - unmarks the first deadline task""";
    public static final String COMMAND_DESC = "Marks the specified task as not done";
    public static final String[] COMMAND_KEYWORDS = {"description"};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * To unmark a task, only the task type and index is required.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length &&
                parameters.containsKey(COMMAND_KEYWORDS[0]);
    }

    /**
     * Marks a task as done.
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
        String description = parameters.get(COMMAND_KEYWORDS[0]);

        try {
            String[] result = Parser.validateIndex(description, tasks.taskCount);
            String taskType = result[0];
            int taskIndex = Integer.parseInt(result[1]) - 1;
            if (!tasks.getTask(taskType, taskIndex).isDone()) {
                feedback.add(PlanitMessages.UNMARK_TASK_REPEAT);
            } else {
                feedback.add(PlanitMessages.UNMARK_TASK_SUCCESS);
                tasks.setTaskStatus(taskType, taskIndex, false);
                feedback.add(tasks.getTask(taskType, taskIndex).toString());
            }
        } catch (EmptyCommandException e) {
            feedback.add(e.getMessage());
        }

        return new CommandResult(feedback);
    }
}

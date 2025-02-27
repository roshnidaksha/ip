package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.Deadline;
import planit.task.Task;
import planit.task.TaskList;
import planit.task.Todo;

import java.util.ArrayList;

/**
 * Handles addition of a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_FORMAT = """
            Format: deadline <task description> /by <task deadline>
            Example: deadline submit quiz /by Fri
            This will add task [D][ ] submit quiz (by: Friday)""";
    public static final String COMMAND_DESC = "Adds a new deadline task to your list";
    public static final String[] COMMAND_KEYWORDS = {"description", "/by"};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * Deadline task requires a description and a deadline.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length &&
                parameters.containsKey(COMMAND_KEYWORDS[0]) && parameters.containsKey(COMMAND_KEYWORDS[1]);
    }

    /**
     * Adds a new deadline task to list.
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
        String deadline = parameters.get(COMMAND_KEYWORDS[1]);

        try {
            Task newTask = new Deadline(description, deadline);
            tasks.addTask("deadline", newTask);
            feedback.add(String.format(PlanitMessages.ADD_TASK_SUCCESS, "deadline"));
            feedback.add(newTask.toString());
            feedback.add(String.format(PlanitMessages.TASK_LIST_SIZE, tasks.taskCount));
        } catch (Exception e) {
            feedback.add(String.format(PlanitMessages.ADD_TASK_FAILURE, "deadline"));
            feedback.add(e.getMessage());
        }

        return new CommandResult(feedback);
    }
}

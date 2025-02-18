package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.task.TaskList;
import planit.task.Todo;

/**
 * Handles addition of a todo task to list.
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND_FORMAT = "todo <task description>";
    public static final String COMMAND_DESC = "Adds a new todo task to your list";
    public static final String[] COMMAND_KEYWORDS = {"description"};


    /**
     * Checks if supplied arguments are valid.
     * Todo task requires only one argument describing the task.
     *
     * @return True if valid, False otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length;
    }

    /**
     * Adds a new todo task to list.
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
        tasks.addTask("todo", new Todo(description));
    }
}

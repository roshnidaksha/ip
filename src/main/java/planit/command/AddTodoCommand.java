package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.task.TaskList;
import planit.task.Todo;

/**
 * Handles addition of a todo task to list.
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_FORMAT = """
            Format: todo <task description>
            Example: todo prepare for CS2113
            This will add task [T][ ] prepare for CS2113 to your list of tasks""";
    public static final String COMMAND_DESC = "Adds a new todo task to your list";
    public static final String[] COMMAND_KEYWORDS = {"description"};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * Todo task requires only one argument describing the task.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length &&
                parameters.containsKey(COMMAND_KEYWORDS[0]);
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

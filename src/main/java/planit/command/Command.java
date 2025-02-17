package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.task.TaskList;

import java.util.HashMap;

/**
 * Abstract class to store command properties.
 */
public abstract class Command {
    public static final String COMMAND_FORMAT = "";
    public static final String COMMAND_DESC = "";
    public static final String[] COMMAND_KEYWORDS = {};

    public boolean isExit = false;

    /**
     * Static data structure that relates command in string representation to
     * actual command object.
     */
    public static HashMap<String, Command> commands = new HashMap<>();
    static {
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("todo", new AddTodoCommand());
        commands.put("deadline", new AddDeadlineCommand());
        commands.put("event", new AddEventCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("bye", new ByeCommand());
    }

    /**
     * Returns Command object of input string command.
     *
     * @param command String representation of command entered by user.
     * @return Command object of string representation.
     */
    public static Command getCommand(String command) {
        return commands.get(command);
    }

    /** Key-value pairs of arguments entered by user. */
    protected HashMap<String, String> parameters = new HashMap<>();
    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Checks if supplied arguments are valid.
     *
     * @return True if valid, False otherwise.
     */
    protected abstract boolean isValidParameters();

    /**
     * Executes the command.
     * Implementation varies according to the command.
     *
     * @param tasks Current list of tasks.
     * @throws InvalidArgumentException If supplied arguments is not valid.
     */
    public abstract void execute(TaskList tasks) throws InvalidArgumentException;
}

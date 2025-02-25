package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.task.TaskList;

import java.util.HashMap;

/**
 * Represents a command that can be executed by the user.
 * <p>
 * This abstract class defines the structure for all commands,
 * providing common properties and methods that each command must implement.
 * </p>
 */
public abstract class Command {
    public static final String COMMAND_WORD = "";
    public static final String COMMAND_FORMAT = "";
    public static final String COMMAND_DESC = "";
    public static final String[] COMMAND_KEYWORDS = {};
    public static final String[] COMMAND_MESSAGE = {};

    public boolean isExit = false;

    /**
     * Static data structure that relates command in string representation to
     * its respective command class.
     */
    public static HashMap<String, Class<? extends Command>> commands = new HashMap<>();
    static {
        commands.put("help", HelpCommand.class);
        commands.put("list", ListCommand.class);
        commands.put("find", FindCommand.class);
        commands.put("todo", AddTodoCommand.class);
        commands.put("deadline", AddDeadlineCommand.class);
        commands.put("event", AddEventCommand.class);
        commands.put("mark", MarkCommand.class);
        commands.put("unmark", UnmarkCommand.class);
        commands.put("delete", DeleteCommand.class);
        commands.put("date", DateCommand.class);
        commands.put("bye", ByeCommand.class);
    }

    /**
     * Retrieves a Command object based on the input string representation of a command.
     *
     * @param commandType String representation of command entered by user.
     * @return Instance of the corresponding Command subclass, or {@code null} if not found.
     * @throws Exception If an error occurs during instantiation.
     */
    public static Command getCommand(String commandType) throws Exception {
        Class<? extends Command> commandClass = commands.get(commandType);
        if (commandClass != null) {
            return commandClass.getDeclaredConstructor().newInstance();
        }
        return null;
    }

    /** Key-value pairs of arguments entered by user. */
    protected HashMap<String, String> parameters = new HashMap<>();

    /**
     * Sets the parameters for the command based on user input.
     *
     * @param parameters HashMap containing the arguments supplied by the user.
     */
    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Checks if supplied arguments are valid.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
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

package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.task.Deadline;
import planit.task.TaskList;
import planit.util.Ui;

/**
 * Displays command guide to user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_FORMAT = """
            Format: help [command]
            NOTE: command is an optional argument
            Example: help - will display the guide for all commands
                     help todo - will display the guide only for todo command
            """;
    public static final String COMMAND_DESC = "Displays guide for commands";
    public static final String[] COMMAND_KEYWORDS = {"description"};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * Help command requires 0 or 1 argument.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.isEmpty() || (parameters.size() == 1 && parameters.containsKey(COMMAND_KEYWORDS[0]));
    }

    /**
     * Displays command guidelines.
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
        if (description == null) {
            for (Class<? extends Command> commandClass : commands.values()) {
                try {
                    String[] commandMessage = (String[]) commandClass.getDeclaredField("COMMAND_MESSAGE").get(null);
                    Ui.showToUser(commandMessage);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    Ui.showToUser("Help information not available for: " + commandClass.getSimpleName());
                }
            }
        } else {
            Class<? extends Command> commandClass = commands.get(description);
            if (commandClass == null) {
                throw new InvalidArgumentException("Unknown command: " + description);
            }
            try {
                String[] commandHelp = (String[]) commandClass.getDeclaredField("COMMAND_MESSAGE").get(null);
                Ui.showToUser(commandHelp);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Ui.showToUser("Help information not available for: " + description);
            }
        }
    }
}

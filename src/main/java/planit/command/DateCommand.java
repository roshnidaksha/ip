package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.handler.DateParser;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.Task;
import planit.task.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles displaying tasks on a specific date.
 */
public class DateCommand extends Command {
    public static final String COMMAND_WORD = "date";
    public static final String COMMAND_FORMAT = """
            Format: date <date>
            Example: date 2025-11-02
            This will display all the tasks on the specified date""";
    public static final String COMMAND_DESC = "Displays tasks on a specific date.";
    public static final String[] COMMAND_KEYWORDS = { "/on" };
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * Date command requires only one argument describing the date.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length &&
                parameters.containsKey(COMMAND_KEYWORDS[0]);
    }

    /**
     * Displays tasks on a specific date.
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
        LocalDateTime date = DateParser.parseDateTime(parameters.get(COMMAND_KEYWORDS[0]));
        String dateString = DateParser.toFileFormat(date);
        HashMap<String, ArrayList<Task>> tasksOnDate = tasks.getTasksOnDate(dateString);

        if (tasksOnDate.isEmpty()) {
            feedback.add(PlanitMessages.FIND_TASK_FAILURE);
        } else {
            feedback.add(PlanitMessages.LIST_SUCCESS);
        }
        return new CommandResult(feedback, tasksOnDate);
    }
}

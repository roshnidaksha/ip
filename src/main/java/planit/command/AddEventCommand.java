package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.Event;
import planit.task.Task;
import planit.task.TaskList;
import planit.task.Todo;

import java.util.ArrayList;

/**
 * Handles addition of an event task to list.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_FORMAT = """
            Format: event <task description> /from <task start time> /to <task end time>
            Example: event watch lec videos /from 26/2/2025 10:00 AM /to 2025/2/26 11:00
            This will add task [E][ ] attend CS2113 lecture (from: Friday 4pm, to: 6pm)""";
    public static final String COMMAND_DESC = "Adds a new event task to your list";
    public static final String[] COMMAND_KEYWORDS = {"description", "/from", "/to"};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * Event task requires a description, a start time and an end time.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length &&
                parameters.containsKey(COMMAND_KEYWORDS[0]) &&
                parameters.containsKey(COMMAND_KEYWORDS[1]) &&
                parameters.containsKey(COMMAND_KEYWORDS[2]);
    }

    /**
     * Adds a new event task to list.
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
        String startTime = parameters.get(COMMAND_KEYWORDS[1]);
        String endTime = parameters.get(COMMAND_KEYWORDS[2]);

        try {
            Task newTask = new Event(description, startTime, endTime);
            tasks.addTask("event", newTask);
            feedback.add(String.format(PlanitMessages.ADD_TASK_SUCCESS, "event"));
            feedback.add(newTask.toString());
            feedback.add(String.format(PlanitMessages.TASK_LIST_SIZE, tasks.taskCount));
            tasks.saveTasks();
        } catch (Exception e) {
            feedback.add(String.format(PlanitMessages.ADD_TASK_FAILURE, "event"));
            feedback.add(e.getMessage());
        }

        return new CommandResult(feedback);
    }
}
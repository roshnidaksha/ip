package planit.command;

import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
import planit.task.Task;
import planit.task.TaskList;
import planit.util.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_FORMAT = """
            Format: clear
            NOTE: Clear takes in no arguments
            Example: clear""";
    public static final String COMMAND_DESC = "Clears all tasks in the list";
    public static final String[] COMMAND_KEYWORDS = {};
    public static final String[] COMMAND_MESSAGE = {COMMAND_WORD + ": " + COMMAND_DESC, COMMAND_FORMAT};

    /**
     * Checks if supplied arguments are valid.
     * To clear all tasks, no arguments is required.
     *
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == COMMAND_KEYWORDS.length;
    }

    /**
     * DClears all tasks.
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
        HashMap<String, ArrayList<Task>> relevantTasks = null;

        String userConfirmation;
        Ui.showToUser(PlanitMessages.DELETE_ALL_TASKS_CONFIRMATION);
        userConfirmation = Ui.getUserInput();

        try {
            if (userConfirmation.equals("yes")) {
                tasks.deleteAllTasks();
                feedback.add(PlanitMessages.DELETE_ALL_TASKS_SUCCESS);
                relevantTasks = tasks.getAllTasks();
                tasks.saveTasks();
            } else {
                feedback.add(PlanitMessages.DELETE_ALL_TASKS_FAILURE);
            }
        } catch (IOException e) {
            feedback.add(PlanitMessages.TASK_SAVE_FAILURE);
            feedback.add(e.getMessage());
        }
        feedback.add(String.format(PlanitMessages.TASK_LIST_SIZE, tasks.taskCount));
        return new CommandResult(feedback, relevantTasks);
    }
}

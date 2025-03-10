package planit.task;

import planit.command.Command;
import planit.command.CommandResult;
import planit.parser.CommandParser;
import planit.messages.PlanitMessages;
import planit.util.Ui;

import java.io.IOException;

/**
 * Represents a collection of tasks.
 * This class provides methods to manage tasks within a list.
 */
public class TaskManager {
    private final TaskList taskList;
    private final CommandParser parser = new CommandParser();

    /**
     * Initializes task list of user.
     */
    public TaskManager() {
        taskList = new TaskList();
    }

    /**
     * Takes in user input and act appropriately.
     */
    public void run() {
        Ui.showTaskManagerWelcomeMessage();
        try {
            taskList.loadTasks();
            Ui.showToUser(PlanitMessages.TASK_RETRIEVE_SUCCESS);
        } catch (IOException e) {
            Ui.showError(PlanitMessages.TASK_RETRIEVE_FAILURE);
            Ui.showError(e.getMessage() + '\n');
        }

        boolean exitTaskManager = false;
        while (!exitTaskManager) {
            try {
                Ui.showToUser("", PlanitMessages.DIVIDER);
                String userInputString = Ui.getUserInput();
                Command c = parser.parse(userInputString);
                CommandResult result = c.execute(taskList);
                Ui.showResultToUser(result);
                exitTaskManager = c.isExit;
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.showToUser(PlanitMessages.DIVIDER);
            }
        }
        Ui.showToUser("");
    }
}

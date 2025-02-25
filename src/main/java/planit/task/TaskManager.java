package planit.task;

import planit.command.Command;
import planit.handler.Parser;
import planit.messages.PlanitMessages;
import planit.util.Ui;

import java.io.IOException;

/**
 * Represents a collection of tasks.
 * This class provides methods to manage tasks within a list.
 */
public class TaskManager {
    private TaskList taskList;
    private Parser parser = new Parser();

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
                c.execute(taskList);
                exitTaskManager = c.isExit;
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.showToUser(PlanitMessages.DIVIDER);
            }
        }
    }
}

package planit.task;

import planit.command.Command;
import planit.handler.Parser;
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
        } catch (IOException e) {
            Ui.showError(Ui.TASK_RETRIEVE_FAILURE);
            Ui.showError(e.getMessage() + '\n');
        }

        boolean exitTaskManager = false;
        while (!exitTaskManager) {
            try {
                String userInputString = Ui.getUserInput();
                Command c = parser.parse(userInputString);
                c.execute(taskList);
                exitTaskManager = c.isExit;
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.showToUser(Ui.DIVIDER);
            }
        }

        try {
            taskList.saveTasks();
        } catch (IOException e) {
            Ui.showError(e.getMessage());
        }
    }
}

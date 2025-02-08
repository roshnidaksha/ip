package planit.task;

import planit.handler.Parser;
import planit.util.Ui;

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

        boolean exitTaskManager = false;
        while (!exitTaskManager) {
            String userInputString = Ui.getUserInput();
            try {
                exitTaskManager = parser.parse(userInputString, taskList);
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.showToUser(Ui.DIVIDER);
            }
        }
    }
}

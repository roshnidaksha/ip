import java.util.Scanner;

/**
 * Represents a collection of tasks.
 * This class provides methods to manage tasks within a list.
 */
public class TaskManager {
    private TaskList taskList;

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
            exitTaskManager = Parser.parse(userInputString, taskList);
            Ui.showToUser(Ui.DIVIDER);
        }
    }
}

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
            String[] commandTypeAndParams = Ui.splitCommandWordAndArgs(userInputString);
            String commandType = commandTypeAndParams[0];
            String commandArgs = commandTypeAndParams[1];

            CommandType commandTypeEnum = CommandType.valueOf(commandType.toUpperCase());
            switch (commandTypeEnum) {
            case LIST:
                taskList.displayAllTasks();
                break;
            case MARK:
            case UNMARK:
                int taskId = Integer.parseInt(commandArgs) - 1;
                taskList.setTaskStatus(taskId, commandTypeEnum == CommandType.MARK);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                taskList.addTask(commandTypeAndParams);
                break;
            case BYE:
                Ui.showTaskManagerExitMessage();
                exitTaskManager = true;
                break;
            default:
                Ui.showError("Invalid command type: " + commandType);
            }
            Ui.showToUser(Ui.DIVIDER);
        }
    }
}

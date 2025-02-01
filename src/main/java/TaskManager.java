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
     *
     * @param scanner Scanner that is used throughout the program.
     */
    public void run(Scanner scanner) {
        System.out.println("""
                      ______ ______
                    _/      Y      \\_
                   // ~~ ~~ | ~~ ~  \\\\
                  // ~ ~ ~~ | ~~~ ~~ \\\\
                 //________.|.________\\\\
                `----------`-'----------'
                """);
        boolean exitTaskManager = false;
        while (!exitTaskManager) {
            System.out.print("> ");
            String userChoice = scanner.nextLine();
            String[] command = userChoice.split(" ", 2);
            if (command[0].equalsIgnoreCase("list")) {
                taskList.displayAllTasks();
            } else if (command[0].equalsIgnoreCase("mark") || command[0].equalsIgnoreCase("unmark")) {
                taskList.toggleTaskStatus(command);
            } else if (command[0].equalsIgnoreCase("bye")) {
                exitTaskManager = true;
            } else {
                taskList.addTask(command);
            }
            Planit.printSeperator();
        }
    }
}

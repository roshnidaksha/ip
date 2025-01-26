import java.util.Scanner;

/**
 * Represents a collection of tasks.
 * This class provides methods to manage tasks within a list.
 */
public class TaskManager {
    private Task[] tasks = new Task[100];
    public int taskCount = 0;

    /**
     * Method to add a task
     *
     * @param description Description of the task to add
     */
    public void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        System.out.println("Added: " + description);
    }

    /**
     * Method to display all tasks in the order in which they were added
     */
    public void displayAllTasks() {
        if (taskCount > 0) {
            System.out.println("Here is a list of your tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + ". " + tasks[i].convertToString());
            }
        } else {
            System.out.println("Great Job! You have no pending tasks /^v^\\");
        }
    }

    /**
     * Method to mark a task as done and not done
     *
     * @param input Array of input entered by user
     */
    public void toggleTaskStatus(String[] input) {
        if (input.length != 2) {
            System.out.println("Wrong number of arguments!");
            return;
        }
        int taskIndex = Integer.parseInt(input[1]) - 1;
        if (input[0].equals("mark")) {
            tasks[taskIndex].setDone(true);
            System.out.println("Task marked as done successfully: " + tasks[taskIndex].convertToString());
        } else {
            tasks[taskIndex].setDone(false);
            System.out.println("Task unmarked successfully: " + tasks[taskIndex].convertToString());
        }
    }

    /**
     * Main method to take in user input and act appropriately.
     *
     * @param scanner Scanner that is used throughout the program
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
            String[] command = userChoice.split(" ");
            if (command[0].equalsIgnoreCase("list")) {
                displayAllTasks();
            } else if (command[0].equalsIgnoreCase("mark") || command[0].equalsIgnoreCase("unmark")) {
                toggleTaskStatus(command);
            } else if (command[0].equalsIgnoreCase("bye")) {
                exitTaskManager = true;
            } else {
                addTask(userChoice.trim());
            }
            Planit.printSeperator();
        }
    }
}

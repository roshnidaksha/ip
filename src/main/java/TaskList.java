/**
 * Represents a collection of tasks.
 * This class provides methods to manage tasks within a list.
 */
public class TaskList {
    private static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    /**
     * Method to add a task
     *
     * @param description Description of the task to add
     */
    public static void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;

        Planit.printSeperator();
        System.out.println("Added: " + description);
        Planit.printSeperator();
    }

    /**
     * Method to display all tasks in the order in which they were added
     */
    public static void displayAllTasks() {
        Planit.printSeperator();
        if (taskCount > 0) {
            System.out.println("Here is a list of your tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + ". " + tasks[i].getDescription());
            }
        } else {
            System.out.println("Great Job! You have no pending tasks /^v^\\");
        }
        Planit.printSeperator();
    }
}

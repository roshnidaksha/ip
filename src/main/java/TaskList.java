public class TaskList {
    private Task[] tasks = new Task[100];

    /** Number of tasks added by user */
    public int taskCount = 0;

    /**
     * Adds a task to Task.
     *
     * @param command Command entered by user.
     */
    public void addTask(String[] command) {
        Task newTask;
        if (command[0].equalsIgnoreCase("todo")) {
            newTask = new Todo(command[1]);
        } else if (command[0].equalsIgnoreCase("deadline")) {
            command = command[1].split(" /by ");
            newTask = new Deadline(command[0], command[1]);
        } else if (command[0].equalsIgnoreCase("event")) {
            command = command[1].split(" /from | /to ");
            newTask = new Event(command[0], command[1], command[2]);
        } else {
            System.out.println("Invalid command");
            return;
        }
        tasks[taskCount] = newTask;
        taskCount++;
        System.out.println("Added: " + newTask.toString());
    }

    /**
     * Displays all tasks in the order in which they were added.
     */
    public void displayAllTasks() {
        if (taskCount > 0) {
            System.out.println("Here is a list of your tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + ". " + tasks[i].toString());
            }
        } else {
            System.out.println("Great Job! You have no pending tasks /^v^\\");
        }
    }

    /**
     * Marks a task as done or not done.
     *
     * @param input Array of input entered by user.
     */
    public void toggleTaskStatus(String[] input) {
        if (input.length != 2) {
            System.out.println("Wrong number of arguments!");
            return;
        }
        int taskIndex = Integer.parseInt(input[1]) - 1;
        if (input[0].equals("mark")) {
            tasks[taskIndex].setDone(true);
            System.out.println("Task marked as done successfully: " + tasks[taskIndex].toString());
        } else {
            tasks[taskIndex].setDone(false);
            System.out.println("Task unmarked successfully: " + tasks[taskIndex].toString());
        }
    }
}

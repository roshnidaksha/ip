package planit.task;

import planit.util.Ui;

public class TaskList {
    private Task[] tasks = new Task[100];

    /** Number of tasks added by user */
    public int taskCount = 0;

    /**
     * Adds a task to Task.
     *
     * @param task Command entered by user.
     */
    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        Ui.showToUser("Added: " + task);
    }

    /**
     * Displays all tasks in the order in which they were added.
     */
    public void displayAllTasks() {
        if (taskCount > 0) {
            Ui.showToUser("Here is a list of your tasks:");
            for (int i = 0; i < taskCount; i++) {
                Ui.showToUser(i + 1 + ". " + tasks[i]);
            }
        } else {
            Ui.showToUser("Great Job! You have no pending tasks /^v^\\");
        }
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskIndex Index of task in taskList.
     */
    public void setTaskStatus(int taskIndex, boolean status) {
        if (tasks[taskIndex].isDone() == status) {
            Ui.showWarning("Task already marked as " + (status ? "done" : "not done"));
        } else {
            tasks[taskIndex].setDone(status);
            Ui.showToUser("Task " + (status ? "marked" : "unmarked") + " successfully");
        }
    }
}

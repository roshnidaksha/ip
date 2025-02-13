package planit.task;

import planit.util.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /** Number of tasks added by user */
    public int taskCount = 0;

    /**
     * Adds a task to task list.
     *
     * @param task Command entered by user.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
        Ui.showToUser("Added task: " + task);
    }

    /**
     * Deletes a task from task list.
     *
     * @param index Index of task to be deleted.
     */
    public void deleteTask(int index) {
        Ui.showToUser("Deleted task: " + tasks.get(index));
        tasks.remove(index);
        taskCount--;
    }

    /**
     * Displays all tasks in the order in which they were added.
     */
    public void displayAllTasks() {
        if (taskCount > 0) {
            Ui.showToUser("Here is a list of your tasks:");
            for (int i = 0; i < taskCount; i++) {
                Ui.showToUser(i + 1 + ". " + tasks.get(i));
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
        if (tasks.get(taskIndex).isDone() == status) {
            Ui.showWarning("Task already marked as " + (status ? "done" : "not done"));
        } else {
            tasks.get(taskIndex).setDone(status);
            Ui.showToUser("Task " + (status ? "marked" : "unmarked") + " successfully");
        }
    }
}

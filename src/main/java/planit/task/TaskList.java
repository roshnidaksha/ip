package planit.task;

import planit.util.Storage;
import planit.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private final Storage storage = new Storage(Ui.FILE_PATH);

    /** Number of tasks added by user */
    public int taskCount = 0;

    public TaskList() {
        try {
            tasks = storage.loadTaskList();
            taskCount = tasks.size();
            Ui.showToUser("Successfully retrieved task list");
        } catch (IOException e) {
            Ui.showError(e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public void saveTasks() throws IOException {
        storage.saveTaskList(tasks);
    }

    public void loadTasks() {
        try {
            tasks = storage.loadTaskList();
        } catch (IOException e) {
            Ui.showError("Unable to load task list: " + e.getMessage());
        }
    }

    /**
     * Adds a task to Task.
     *
     * @param task Command entered by user.
     */
    public void addTask(Task task) {
        tasks.add(task);
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

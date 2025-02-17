package planit.task;

import planit.util.Storage;
import planit.util.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    private HashMap<String, ArrayList<Task>> tasksMap;
    private final Storage storage = new Storage(Ui.FILE_PATH);

    /**
     * Number of tasks added by user
     */
    public int taskCount = 0;

    public TaskList() {
        try {
            loadTasks();
            Ui.showToUser(Ui.TASK_RETRIEVE_SUCCESS);
        } catch (IOException e) {
            Ui.showToUser(Ui.TASK_RETRIEVE_FAILURE);
            Ui.showError(e.getMessage() + '\n');
            tasksMap = new HashMap<>();
            tasksMap.put("todo", new ArrayList<>());
            tasksMap.put("deadline", new ArrayList<>());
            tasksMap.put("event", new ArrayList<>());
        }
    }

    public void saveTasks() throws IOException {
        storage.saveTaskList(tasksMap);
    }

    public void loadTasks() throws IOException {
        ArrayList<Task> allTasks = storage.loadTaskList();
        taskCount = allTasks.size();

        tasksMap = new HashMap<>();
        tasksMap.put("todo", new ArrayList<>());
        tasksMap.put("deadline", new ArrayList<>());
        tasksMap.put("event", new ArrayList<>());

        for (Task task : allTasks) {
            String taskType = task.getTaskType();
            switch (taskType) {
            case "T":
                tasksMap.get("todo").add(task);
                break;
            case "D":
                tasksMap.get("deadline").add(task);
                break;
            case "E":
                tasksMap.get("event").add(task);
                break;
            }
        }
    }

    /**
     * Adds a task to task list.
     *
     * @param task Command entered by user.
     */
    public void addTask(String taskType, Task task) {
        tasksMap.get(taskType).add(task);
        taskCount++;
        Ui.showToUser("Added task: " + task);
    }

    /**
     * Deletes a task from task list.
     *
     * @param index Index of task to be deleted.
     */
    public void deleteTask(String taskType, int index) {
        Ui.showToUser("Deleted task: " + tasksMap.get(taskType).get(index));
        tasksMap.get(taskType).remove(index);
        taskCount--;
    }

    /**
     * Displays all tasks in the order in which they were added.
     */
    public void displayAllTasks() {
        if (taskCount > 0) {
            Ui.showToUser("Here is a list of your tasks:");
            for (String taskType : tasksMap.keySet()) {
                Ui.showToUser(taskType + ":");
                int length = tasksMap.get(taskType).size();
                for (int i = 0; i < length; i++) {
                    Ui.showToUser(i + 1 + ". " + tasksMap.get(taskType).get(i));
                }
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
    public void setTaskStatus(String taskType, int taskIndex, boolean status) {
        if (tasksMap.get(taskType).get(taskIndex).isDone() == status) {
            Ui.showWarning("Task already marked as " + (status ? "done" : "not done"));
        } else {
            tasksMap.get(taskType).get(taskIndex).setDone(status);
            Ui.showToUser("Task " + (status ? "marked" : "unmarked") + " successfully");
        }
    }
}

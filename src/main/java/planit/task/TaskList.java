package planit.task;

import planit.messages.PlanitMessages;
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

    /**
     * Constructs a task list.
     * <p>
     * If tasks can be loaded from file storage, it will be loaded.
     * Otherwise, an empty task list will be created.
     * </p>
     */
    public TaskList() {
        try {
            loadTasks();
        } catch (IOException e) {
            Ui.showToUser(PlanitMessages.TASK_RETRIEVE_FAILURE);
            Ui.showError(e.getMessage() + '\n');
            tasksMap = new HashMap<>();
            tasksMap.put("todo", new ArrayList<>());
            tasksMap.put("deadline", new ArrayList<>());
            tasksMap.put("event", new ArrayList<>());
        }
    }

    /**
     * Returns the task of a specific type and index.
     *
     * @param taskType  Type of task.
     * @param taskIndex Index of task.
     * @return Task object.
     */
    public Task getTask(String taskType, int taskIndex) {
        return tasksMap.get(taskType).get(taskIndex);
    }

    /**
     * Returns all tasks.
     *
     * @return HashMap of all tasks.
     */
    public HashMap<String, ArrayList<Task>> getAllTasks() {
        return tasksMap;
    }

    /**
     * Saves tasks to file storage.
     *
     * @throws IOException If file cannot be written.
     */
    public void saveTasks() throws IOException {
        storage.saveTaskList(tasksMap);
    }

    /**
     * Loads tasks from file storage.
     *
     * @throws IOException If file cannot be read.
     */
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
    }

    /**
     * Deletes a task from task list.
     *
     * @param index Index of task to be deleted.
     */
    public Task deleteTask(String taskType, int index) {
        Task taskToDelete = tasksMap.get(taskType).get(index);
        tasksMap.get(taskType).remove(index);
        taskCount--;
        return taskToDelete;
    }

    /**
     * Displays all tasks in the order in which they were added.
     */
    public ArrayList<String> displayAllTasks() {
        ArrayList<String> allTasks = new ArrayList<>();
        for (String taskType : tasksMap.keySet()) {
            allTasks.add(taskType + ":");
            int length = tasksMap.get(taskType).size();
            for (int i = 0; i < length; i++) {
                allTasks.add(i + 1 + ". " + tasksMap.get(taskType).get(i));
            }
        }
        return allTasks;
    }

    /**
     * Displays all tasks that are due/occur on a specific date.
     *
     * @param date Date to search for tasks.
     */
    public ArrayList<String> displayTasksOnDate(String date) {
        ArrayList<String> tasksOnDateString = new ArrayList<>();
        for (String taskType : tasksMap.keySet()) {
            if (taskType.equalsIgnoreCase("todo")) {
                continue;
            }
            for (Task task : tasksMap.get(taskType)) {
                if (task.toFileFormat().contains(date)) {
                    tasksOnDateString.add(task.toString());
                }
            }
        }
        return tasksOnDateString;
    }

    /**
     * Displays all tasks that contain the keyword.
     *
     * @param keyword Keyword to search for in tasks.
     */
    public ArrayList<String> displayTasksWithKeyword(String keyword) {
        ArrayList<String> tasksWithKeyword = new ArrayList<>();
        for (String taskType : tasksMap.keySet()) {
            for (Task task : tasksMap.get(taskType)) {
                if (task.getDescription().contains(keyword)) {
                    tasksWithKeyword.add(task.toString());
                }
            }
        }
        return tasksWithKeyword;
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskIndex Index of task in taskList.
     */
    public void setTaskStatus(String taskType, int taskIndex, boolean status) {
        tasksMap.get(taskType).get(taskIndex).setDone(status);
    }
}

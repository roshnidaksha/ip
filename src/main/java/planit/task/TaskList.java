package planit.task;

import planit.messages.PlanitMessages;
import planit.util.Storage;
import planit.util.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
     * Deletes all tasks from task list.
     */
    public void deleteAllTasks() {
        tasksMap.get("todo").clear();
        tasksMap.get("deadline").clear();
        tasksMap.get("event").clear();
        taskCount = 0;
    }

    /**
     * Displays all tasks that are due/occur on a specific date.
     *
     * @param date Date to search for tasks.
     */
    public HashMap<String, ArrayList<Task>> getTasksOnDate(String date) {
        return tasksMap.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .filter(task -> task.toFileFormat().contains(date))
                    .collect(Collectors.toCollection(ArrayList::new)),
                (a, b) -> b, HashMap::new
            )).entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, HashMap::new));
    }

    /**
     * Displays all tasks that contain the keyword.
     *
     * @param keyword Keyword to search for in tasks.
     */
    public HashMap<String, ArrayList<Task>> getTasksWithKeyword(String keyword) {
        return tasksMap.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .filter(task -> task.getDescription().contains(keyword))
                    .collect(Collectors.toCollection(ArrayList::new)),
                (a, b) -> b, HashMap::new
            )).entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, HashMap::new));
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

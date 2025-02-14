package planit.handler;

import planit.command.CommandType;
import planit.exceptions.EmptyCommandException;
import planit.task.Deadline;
import planit.task.Event;
import planit.task.Task;
import planit.task.TaskList;
import planit.task.Todo;
import planit.util.Ui;

import java.io.IOException;

public class Parser {
    /**
     * Parses user input string.
     *
     * @param userInputString Input entered by user.
     * @param taskList List of tasks of user.
     * @return True if input is bye, False otherwise.
     */
    public boolean parse(String userInputString, TaskList taskList) throws EmptyCommandException, IOException {
        boolean isExit = false;
        String[] commandTypeAndParams = Ui.splitCommandWordAndArgs(userInputString);
        String commandType = commandTypeAndParams[0];
        String commandArgs = commandTypeAndParams[1];

        CommandType commandTypeEnum;
        try {
            commandTypeEnum = CommandType.valueOf(commandType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid command type: " + commandType);
        }

        switch (commandTypeEnum) {
        case LIST:
            taskList.displayAllTasks();
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            validateIndex(commandArgs, taskList.taskCount);
            int taskId = Integer.parseInt(commandArgs);
            if (commandTypeEnum == CommandType.MARK || commandTypeEnum == CommandType.UNMARK) {
                taskList.setTaskStatus(taskId - 1, commandTypeEnum == CommandType.MARK);
            } else {
                taskList.deleteTask(taskId - 1);
            }
            break;
        case TODO:
            taskList.addTask(parseTodo(commandArgs));
            break;
        case DEADLINE:
            taskList.addTask(parseDeadline(commandArgs));
            break;
        case EVENT:
            taskList.addTask(parseEvent(commandArgs));
            break;
        case BYE:
            Ui.showTaskManagerExitMessage();
            taskList.saveTasks();
            isExit = true;
            break;
        default:
            throw new IllegalArgumentException("Invalid command type: " + commandTypeEnum);
        }
        return isExit;
    }

    /**
     * Checks if index entered by user is valid.
     *
     * @param index Index of task entered by user.
     * @param taskCount Number of tasks currently stored.
     * @throws EmptyCommandException If index of task is missing.
     */
    private void validateIndex(String index, int taskCount) throws EmptyCommandException {
        if (index.isEmpty()) {
            throw new EmptyCommandException("Index of task required.");
        }
        try {
            int taskId = Integer.parseInt(index);
            if (taskId < 1 || taskId > taskCount) {
                throw new IndexOutOfBoundsException("Invalid task id: " + taskId);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task index must be a valid number.");
        }
    }

    /**
     * Parse a todo command.
     *
     * @param task Task details entered by user as a string.
     * @return Todo task object.
     * @throws EmptyCommandException If todo task is empty or null.
     */
    private Task parseTodo(String task) throws EmptyCommandException {
        if (task.trim().isEmpty()) {
            throw new EmptyCommandException("Todo task description cannot be empty.");
        }
        return new Todo(task);
    }

    /**
     * Parse a deadline command.
     *
     * @param task Task details entered by user as a string.
     * @return Deadline task object.
     * @throws EmptyCommandException If deadline for task is null.
     */
    private Task parseDeadline(String task) throws EmptyCommandException {
        String[] parts = task.split(" /by ", 2);
        if (parts.length < 2) {
            throw new EmptyCommandException("Deadline task is missing deadline or description.");
        }
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Parse an Event command.
     *
     * @param task Task details entered by user as a string.
     * @return Event task object.
     * @throws EmptyCommandException If event timing is null.
     */
    private Task parseEvent(String task) throws EmptyCommandException {
        String[] parts = task.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new EmptyCommandException("Event task is missing timing or description.");
        }
        return new Event(parts[0], parts[1], parts[2]);
    }
}

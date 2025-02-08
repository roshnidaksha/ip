public class Parser {
    /**
     * Parses user input string.
     *
     * @param userInputString Input entered by user.
     * @param taskList List of tasks of user.
     * @return True if input is bye, False otherwise.
     */
    public boolean parse(String userInputString, TaskList taskList) {
        boolean isExit = false;
        String[] commandTypeAndParams = Ui.splitCommandWordAndArgs(userInputString);
        String commandType = commandTypeAndParams[0];
        String commandArgs = commandTypeAndParams[1];

        CommandType commandTypeEnum = null;
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
            int taskId = Integer.parseInt(commandArgs);
            if (taskId < 1 || taskId > taskList.taskCount) {
                throw new IndexOutOfBoundsException("Invalid task id: " + taskId);
            }
            taskList.setTaskStatus(taskId - 1, commandTypeEnum == CommandType.MARK);
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
            isExit = true;
            break;
        default:
            throw new IllegalArgumentException("Invalid command type: " + commandTypeEnum);
        }
        return isExit;
    }

    /**
     * Parse a todo command.
     *
     * @param task Task details entered by user as a string.
     * @return Todo task object.
     * @throws IllegalArgumentException If todo task is empty or null.
     */
    private Task parseTodo(String task) throws IllegalArgumentException {
        if (task.trim().isEmpty()) {
            throw new IllegalArgumentException("Todo task description cannot be empty.");
        }
        return new Todo(task);
    }

    /**
     * Parse a deadline command.
     *
     * @param task Task details entered by user as a string.
     * @return Deadline task object.
     * @throws IllegalArgumentException If deadline for task is null.
     */
    private Task parseDeadline(String task) throws IllegalArgumentException {
        String[] parts = task.split(" /by ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Deadline task is missing deadline or description.");
        }
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Parse an Event command.
     *
     * @param task Task details entered by user as a string.
     * @return Event task object.
     * @throws IllegalArgumentException If event timing is null.
     */
    private Task parseEvent(String task) throws IllegalArgumentException {
        String[] parts = task.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Event task is missing timing or description.");
        }
        return new Event(parts[0], parts[1], parts[2]);
    }
}

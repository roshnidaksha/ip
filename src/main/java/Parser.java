public class Parser {
    public static boolean parse(String userInputString, TaskList taskList) {
        String[] commandTypeAndParams = Ui.splitCommandWordAndArgs(userInputString);
        String commandType = commandTypeAndParams[0];
        String commandArgs = commandTypeAndParams[1];

        CommandType commandTypeEnum = CommandType.valueOf(commandType.toUpperCase());
        switch (commandTypeEnum) {
        case LIST:
            taskList.displayAllTasks();
            break;
        case MARK:
        case UNMARK:
            int taskId = Integer.parseInt(commandArgs);
            taskList.setTaskStatus(taskId - 1, commandTypeEnum == CommandType.MARK);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            taskList.addTask(commandTypeAndParams);
            break;
        case BYE:
            Ui.showTaskManagerExitMessage();
            return true;
        default:
            Ui.showError("Invalid command type: " + commandType);
        }
        return false;
    }
}

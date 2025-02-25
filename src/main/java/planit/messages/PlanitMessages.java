package planit.messages;

/**
 * Contains predefined messages used throughout the {@code Planit} application.
 * <p>
 * This class provides static final strings for various system responses.
 * </p>
 */
public class PlanitMessages {
    public static final String PLANIT_MESSAGE_WELCOME = "          Welcome to Planit, task management system!\n";
    public static final String PLANIT_MESSAGE_GOODBYE = "Bye. Have a nice day!";
    public static final String PLANIT_INVALID_OPTION = "Please enter a valid option!";

    public static final String TASK_MANAGER_MESSAGE_WELCOME = "Welcome, I am your task manager!";
    public static final String TASK_MANAGER_MESSAGE_GOODBYE = "Hope you took note of some important tasks! Bye!";

    public static final String COMMAND_PLANIT_DESC = "1. Type \"planit\" or \"1\" to manage your tasks";
    public static final String COMMAND_PLAY_DESC = "2. Type \"play\" or \"2\" to have a break and play games";
    public static final String COMMAND_EXIT_DESC = "3. Type \"bye\" or \"3\" to quit the session";

    public static final String TASK_RETRIEVE_SUCCESS = "================ Tasks retrieved successfully! ===============";
    public static final String TASK_RETRIEVE_FAILURE = "================== Could not retrieve tasks! =================";
    public static final String TASK_SAVE_SUCCESS = "================== Tasks Saved successfully! ================";
    public static final String TASK_SAVE_FAILURE = "==================== Could not save tasks! ==================";

    public static final String DIVIDER = "->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->";

    public static final String ADD_TASK_SUCCESS = "Successfully added %s task:";
    public static final String ADD_TASK_FAILURE = "Failed to add %s task";
    public static final String DELETE_TASK_SUCCESS = "Successfully deleted task: %s";
    public static final String DELETE_TASK_FAILURE = "Failed to delete task: %s";
    public static final String LIST_SUCCESS = "Here is a list of your tasks:";
    public static final String LIST_EMPTY = "Great Job! You have no pending tasks /^v^\\";
    public static final String MARK_TASK_SUCCESS = "Successfully marked task as done";
    public static final String MARK_TASK_REPEAT = "Task already marked as done";
    public static final String UNMARK_TASK_SUCCESS = "Successfully marked task as not done";
    public static final String UNMARK_TASK_REPEAT = "Task already marked as not done";
    public static final String FIND_TASK_FAILURE = "There are no tasks that satisfy the condition";

    public static final String TASK_LIST_SIZE = "You have %d tasks in the list";
}

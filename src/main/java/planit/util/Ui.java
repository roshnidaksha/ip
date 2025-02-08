package planit.util;

import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String PLANIT_LOGO = """
            +------------------------------------------------------------+
            |       _______   __                      __    __           |
            |      /       \\ /  |                    /  |  /  |          |
            |      $$$$$$$  |$$ |  ______   _______  $$/  _$$ |_         |
            |      $$ |__$$ |$$ | /      \\ /       \\ /  |/ $$   |        |
            |      $$    $$/ $$ | $$$$$$  |$$$$$$$  |$$ |$$$$$$/         |
            |      $$$$$$$/  $$ | /    $$ |$$ |  $$ |$$ |  $$ | __       |
            |      $$ |      $$ |/$$$$$$$ |$$ |  $$ |$$ |  $$ |/  |      |
            |      $$ |      $$ |$$    $$ |$$ |  $$ |$$ |  $$  $$/       |
            |      $$/       $$/  $$$$$$$/ $$/   $$/ $$/    $$$$/        |
            |                                                            |
            +------------------------------------------------------------+
            """;

    private static final String TASK_MANAGER_LOGO = """
                  ______ ______
                _/      Y      \\_
               // ~~ ~~ | ~~ ~  \\\\
              // ~ ~ ~~ | ~~~ ~~ \\\\
             //________.|.________\\\\
            `----------`-'----------'
            """;

    private static final String PLANIT_MESSAGE_WELCOME = "          Welcome to Planit, task management system!\n";
    private static final String PLANIT_MESSAGE_GOODBYE = "Bye. Have a nice day!";

    private static final String TASK_MANAGER_MESSAGE_WELCOME = "Welcome, I am your task manager!";
    private static final String TASK_MANAGER_GOODBYE = "Hope you took note of some important tasks! Bye!";

    private static final String COMMAND_PLANIT_DESC = "1. Type \"planit\" or \"1\" to manage your tasks";
    private static final String COMMAND_PLAY_DESC = "2. Type \"play\" or \"2\" to have a break and play games";
    private static final String COMMAND_EXIT_DESC = "3. Type \"bye\" or \"3\" to quit the session";

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String WARNING_PREFIX = "[WARNING] ";

    public static final String DIVIDER = "->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->";

    public static void showPlanitWelcomeMessage() {
        System.out.println(PLANIT_LOGO);
        showToUser(PLANIT_MESSAGE_WELCOME);
    }

    public static void showTaskManagerWelcomeMessage() {
        System.out.println(TASK_MANAGER_LOGO);
        showToUser(TASK_MANAGER_MESSAGE_WELCOME);
    }

    /**
     * Lists all available commands in Planit.
     */
    public static void showPlanitUserGuide() {
        showToUser(DIVIDER, COMMAND_PLANIT_DESC, COMMAND_PLAY_DESC, COMMAND_EXIT_DESC, DIVIDER);
    }

    /**
     * Displays exit message for task manager.
     */
    public static void showTaskManagerExitMessage() {
        showToUser(TASK_MANAGER_GOODBYE);
    }

    /**
     * Shows multiple messages to the user.
     *
     * @param message Messages to be shown.
     */
    public static void showToUser(String... message) {
        for (String m : message) {
            showToUser(m);
        }
    }

    /**
     * Shows a single message to user.
     *
     * @param message Message to be shown.
     */
    public static void showToUser(String message) {
        System.out.println(message);
    }

    /**
     * Shows an error message to user.
     *
     * @param message Error message to display.
     */
    public static void showError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    /**
     * Shows a warning message to user.
     *
     * @param message Warning message to display.
     */
    public static void showWarning(String message) {
        System.out.println(WARNING_PREFIX + message);
    }

    /**
     * Displays the goodbye message and exits the runtime.
     */
    public static void exitProgram() {
        showToUser(PLANIT_MESSAGE_GOODBYE, DIVIDER, DIVIDER);
        System.exit(0);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return Full line entered by the user.
     */
    public static String getUserInput() {
        System.out.print("> ");
        String inputLine = SCANNER.nextLine();
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }

    /**
     * Splits raw user input into command word and command arguments string.
     *
     * @param rawUserInput Full line entered by the user.
     * @return size 2 array; first element is the command type and second element is the arguments string
     */
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0], "" };
    }
}

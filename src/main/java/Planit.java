import java.util.Scanner;

public class Planit {
    static String logo = """
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

    static Scanner sc = new Scanner(System.in);

    /**
     * Prints a line for enhanced readability.
     */
    static void printSeperator() {
        System.out.println("______________________________________________________________");
    }

    /**
     * Display greeting message during start of new session
     */
    static void greet() {
        System.out.println("What can I do for you?\n");
        System.out.println("1. Type \"planit\" or \"1\" to manage your tasks");
        System.out.println("2. Type \"play\" or \"2\" to have a break and play games");
        System.out.println("3. Type \"bye\" or \"3\" to quit the session");
        printSeperator();
        System.out.print("> ");
    }

    /**
     * Display exit message before closing Planit
     */
    static void exit() {
        System.out.println("Bye. Have a nice day!");
        printSeperator();
    }

    /**
     * Manage tasks according to user preferences
     */
    static void manageTask() {
        System.out.println("""
                      ______ ______
                    _/      Y      \\_
                   // ~~ ~~ | ~~ ~  \\\\
                  // ~ ~ ~~ | ~~~ ~~ \\\\
                 //________.|.________\\\\
                `----------`-'----------'
                """);
        boolean exitTaskManager = false;
        while (!exitTaskManager) {
            System.out.println("1. Type \"list\" or \"1\" to display the list of tasks");
            System.out.println("2. Type \"bye\" or \"2\" to quit the session");
            System.out.println("3. Type in the task description you want to add");
            System.out.print("> ");
            String userChoice = sc.nextLine();
            if (userChoice.equalsIgnoreCase("list") || userChoice.equals("1")) {
                TaskList.displayAllTasks();
            } else if (userChoice.equalsIgnoreCase("bye") || userChoice.equals("2")) {
                exitTaskManager = true;
            } else {
                TaskList.addTask(userChoice.trim());
            }
        }
    }

    /**
     * Display game options to user and redirect to correct class
     */
    static void playGame() {
        System.out.println("""
                 o   \\ o /  _ o         __|    \\ /     |__        o _  \\ o /   o
                /|\\    |     /\\   ___\\o   \\o    |    o/    o/__   /\\     |    /|\\
                / \\   / \\   | \\  /)  |    ( \\  /o\\  / )    |  (\\  / |   / \\   / \\
                """);
        boolean exitGameMode = false;
        while (!exitGameMode) {
            System.out.println("Looking for something fun?");
            System.out.println("1. Type \"parrot\" or \"1\" to play the parrot game! -- ");
            System.out.println("2. Type \"bye\" or \"2\" to quit the session");
            System.out.print("> ");
            String userChoice = sc.nextLine();
            if (userChoice.equalsIgnoreCase("parrot") || userChoice.equals("1")) {
                Echo.echo();
            } else if (userChoice.equalsIgnoreCase("bye") || userChoice.equals("2")) {
                exitGameMode = true;
            } else {
                System.out.println("Please enter a valid option!");
            }
        }
    }

    /**
     * The main method serves as the entry point for the program.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println(logo);
        System.out.println("          Welcome to Planit, task management system!\n");

        boolean exitPlanit = false;
        while (!exitPlanit) {
            greet();
            String userChoice = sc.nextLine();
            if (userChoice.equalsIgnoreCase("planit") || userChoice.equals("1")) {
                manageTask();
            } else if (userChoice.equalsIgnoreCase("play") || userChoice.equals("2")) {
                playGame();
            } else if (userChoice.equalsIgnoreCase("bye") || userChoice.equals("3")) {
                exitPlanit = true;
            } else {
                System.out.println("Please enter a valid option!");
            }
            printSeperator();
        }

        exit();
    }
}

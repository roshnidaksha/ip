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

    /**
     * Prints a line for enhanced readability.
     */
    static void printSeperator() {
        System.out.println("______________________________________________________________");
    }

    /**
     * Displays greeting message during start of new session.
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
     * Displays exit message before closing Planit.
     */
    static void exit() {
        System.out.println("Bye. Have a nice day!");
        printSeperator();
    }

    /**
     * Displays game options to user and redirect to correct class.
     */
    static void playGame(Scanner scanner) {
        System.out.println("""
                 o   \\ o /  _ o         __|    \\ /     |__        o _  \\ o /   o
                /|\\    |     /\\   ___\\o   \\o    |    o/    o/__   /\\     |    /|\\
                / \\   / \\   | \\  /)  |    ( \\  /o\\  / )    |  (\\  / |   / \\   / \\
                """);
        boolean exitGameMode = false;
        while (!exitGameMode) {
            System.out.println("Looking for something fun?");
            System.out.println("1. Type \"parrot\" or \"1\" to play the parrot game! -- ");
            System.out.println("2. Type \"bye\" or \"2\" to quit the game session");
            System.out.print("> ");
            String userChoice = scanner.nextLine();
            if (userChoice.equalsIgnoreCase("parrot") || userChoice.equals("1")) {
                Echo.echo();
            } else if (userChoice.equalsIgnoreCase("bye") || userChoice.equals("2")) {
                exitGameMode = true;
            } else {
                System.out.println("Please enter a valid option!");
            }
        }
        printSeperator();
    }

    /**
     * Gets input from user regarding available choices.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println(logo);
        System.out.println("          Welcome to Planit, task management system!\n");

        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        boolean exitPlanit = false;
        while (!exitPlanit) {
            greet();
            String userChoice = sc.nextLine();
            if (userChoice.equalsIgnoreCase("planit") || userChoice.equals("1")) {
                taskManager.run(sc);
            } else if (userChoice.equalsIgnoreCase("play") || userChoice.equals("2")) {
                playGame(sc);
            } else if (userChoice.equalsIgnoreCase("bye") || userChoice.equals("3")) {
                exitPlanit = true;
            } else {
                System.out.println("Please enter a valid option!");
            }
        }

        exit();
    }
}

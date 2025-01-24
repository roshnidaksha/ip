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
     * Display greeting message during start of new session
     */
    static void greet() {
        System.out.println("What can I do for you?");
        printSeperator();
    }

    /**
     * Display exit message before closing Planit
     */
    static void exit() {
        System.out.println("Bye. Have a nice day!");
        printSeperator();
    }

    /**
     * The main method serves as the entry point for the program.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println(logo);
        System.out.println("          Welcome to Planit, task management system!\n");
        greet();

        Scanner sc = new Scanner(System.in);
        System.out.println("Looking for something fun?");
        System.out.print("Type \"parrot\" to play the parrot game! -- ");
        String userChoice = sc.nextLine();
        if (userChoice.equalsIgnoreCase("parrot")) {
            Echo.echo();
        }

        exit();
    }
}

import java.util.Scanner;

/**
 * Echoes commands entered by the user until user inputs "bye".
 */
public class Echo {
    public static void echo() {
        Planit.printSeperator();
        String parrot = """
                  \\\\
                  (o>
                  //\\
                 _V_/__
                  ||
                  ||
                """;
        System.out.println(parrot);

        System.out.println("Fun choice!");
        System.out.println("The parrot repeats anything you say (o> ...");
        System.out.println("Say \"bye\" to stop playing");
        System.out.println("Have fun /^v^\\");

        Scanner sc = new Scanner(System.in);
        boolean exitEchoMode = false;
        while (!exitEchoMode) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exitEchoMode = true;
            }
            System.out.print("\\\\\n" + "(o> ");
            System.out.println(input);
        }

        Planit.printSeperator();
    }
}

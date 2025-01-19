public class Planit {
    static void printSeperator() {
        System.out.println("______________________________________________________________");
    }

    static void greet() {
        System.out.println("What can I do for you?");
        printSeperator();
    }

    static void exit() {
        System.out.println("Bye. Have a nice day!");
        printSeperator();
    }

    public static void main(String[] args) {
        String logo = """
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
        System.out.println(logo);
        System.out.println("          Welcome to Planit, task management system!\n");
        greet();
        exit();
    }
}

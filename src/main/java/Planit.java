import java.util.Scanner;

public class Planit {
    /**
     * Gets input from user regarding available choices.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Ui.showPlanitWelcomeMessage();

        TaskManager taskManager = new TaskManager();

        boolean exitPlanit = false;
        while (!exitPlanit) {
            Ui.showPlanitUserGuide();
            String userChoice = Ui.getUserInput();
            if (userChoice.equalsIgnoreCase("planit") || userChoice.equals("1")) {
                taskManager.run();
            } else if (userChoice.equalsIgnoreCase("play") || userChoice.equals("2")) {
                Echo.echo();
            } else if (userChoice.equalsIgnoreCase("bye") || userChoice.equals("3")) {
                exitPlanit = true;
            } else {
                Ui.showError("Please enter a valid option!");
            }
        }

        Ui.exitProgram();
    }
}

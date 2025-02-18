package planit;

import planit.messages.PlanitMessages;
import planit.task.TaskManager;
import planit.util.Echo;
import planit.util.Ui;

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
                Ui.showError(PlanitMessages.PLANIT_INVALID_OPTION);
            }
        }

        Ui.exitProgram();
    }
}

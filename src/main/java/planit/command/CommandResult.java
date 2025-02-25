package planit.command;

import planit.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandResult {
    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final List<String> feedbackToUser;

    /** The list of tasks that were produced by the command */
    private final HashMap<String, ArrayList<Task>> relevantTasks;

    /**
     * Constructs a {@code CommandResult} with feedback to user.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     */
    public CommandResult(List<String> feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = null;
    }

    /**
     * Constructs a {@code CommandResult} with a single feedback to user.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = List.of(feedbackToUser);
        this.relevantTasks = null;
    }

    /**
     * Constructs a {@code CommandResult} with feedback to user and relevant tasks.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param relevantTasks The list of tasks that were produced by the command.
     */
    public CommandResult(List<String> feedbackToUser, HashMap<String, ArrayList<Task>> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Returns a list of tasks that was produced by the command, if any.
     */
    public HashMap<String, ArrayList<Task>> getRelevantTasks() {
        return relevantTasks;
    }
}

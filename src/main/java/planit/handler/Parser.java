package planit.handler;

import planit.command.Command;
import planit.exceptions.EmptyCommandException;
import planit.exceptions.InvalidArgumentException;
import planit.util.Ui;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses uses input.
 */
public class Parser {
    /**
     * Parses user input string.
     *
     * @param userInputString Input entered by user.
     * @return Command object of command entered by user.
     * @throws InvalidArgumentException If arguments entered by user is invalid.
     */
    public Command parse(String userInputString) throws InvalidArgumentException {
        String[] commandTypeAndParams = Ui.splitCommandWordAndArgs(userInputString);
        String commandType = commandTypeAndParams[0];
        String commandArgs = commandTypeAndParams[1];

        Command command = Command.getCommand(commandType.toLowerCase());
        if (command == null) {
            throw new InvalidArgumentException("Invalid command type: " + commandType);
        }

        parseKeyValuePairs(command, commandArgs);
        return command;
    }

    /**
     * Parses user input string and extract key value pairs from it.
     * Keys are preceded using "/".
     *
     * @param command Command object entered by user.
     * @param input User input.
     * @throws InvalidArgumentException If key-value pairs cannot be extracted.
     */
    private void parseKeyValuePairs(Command command, String input) throws InvalidArgumentException {
        HashMap<String, String> keyValueMap = new HashMap<>();

        if (input.isEmpty()) {
            return;
        }

        String regex = "^(.*?)\\s*(?:/(\\w+)\\s+([^/]+))*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input.trim());

        if (!matcher.matches()) {
            throw new InvalidArgumentException("Invalid input format.");
        }

        String description = matcher.group(1).trim();
        keyValueMap.put("description", description);

        for (int i = 2; i <= matcher.groupCount(); i += 2) {
            if (matcher.group(i) != null && matcher.group(i + 1) != null) {
                String key = "/" + matcher.group(i).trim();
                String value = matcher.group(i + 1).trim();
                if (value.contains("/")) {
                    throw new InvalidArgumentException("Invalid input: Unexpected '/' found in value for key " + key);
                }
                keyValueMap.put(key, value);
            }
        }

        command.setParameters(keyValueMap);
    }

    /**
     * Checks if index entered by user is valid and return the task type and index.
     * Index format is <task type><task index>
     *
     * @param index Index of task entered by user.
     * @param taskCount Number of tasks currently stored.
     * @return Task type and task index as string.
     * @throws EmptyCommandException If index of task is missing or invalid.
     */
    public static String[] validateIndex(String index, int taskCount) throws EmptyCommandException {
        if (index.isEmpty()) {
            throw new EmptyCommandException("Index of task required.");
        }
        try {
            String taskType = index.substring(0, 1);
            switch (taskType) {
            case "t":
                taskType = "todo";
                break;
            case "d":
                taskType = "deadline";
                break;
            case "e":
                taskType = "event";
                break;
            default:
                throw new IllegalArgumentException("Invalid or missing task type.");
            }

            int taskId = Integer.parseInt(index.substring(1));
            if (taskId < 1 || taskId > taskCount) {
                throw new IndexOutOfBoundsException("Invalid task id: " + taskId);
            }
            return new String[] {taskType, String.valueOf(taskId)};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task index must be a valid number.");
        }
    }
}

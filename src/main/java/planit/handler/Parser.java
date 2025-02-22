package planit.handler;

import planit.command.Command;
import planit.exceptions.EmptyCommandException;
import planit.exceptions.InvalidArgumentException;
import planit.messages.PlanitExceptionMessages;
import planit.messages.PlanitMessages;
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

        Command command;
        try {
            command = Command.getCommand(commandType.toLowerCase());
        } catch (Exception e) {
            throw new InvalidArgumentException(String.format(PlanitExceptionMessages.INVALID_COMMAND, commandType));
        }
        if (command == null) {
            throw new InvalidArgumentException(String.format(PlanitExceptionMessages.INVALID_COMMAND, commandType));
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

        String[] parts = input.trim().split("/", 2);
        String description = parts[0].trim();
        if (!description.isEmpty()) {
            keyValueMap.put("description", description);
        }
        if (parts.length == 1) {
            command.setParameters(keyValueMap);
            return;
        }

        String keyValuePart = parts[1];
        String regex = "(\\w+)\\s+(.*?)(?=\\s+/|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(keyValuePart);

        while (matcher.find()) {
            String key = "/" + matcher.group(1).trim();
            String value = matcher.group(2).trim();
            if (value.contains("/")) {
                throw new InvalidArgumentException(PlanitExceptionMessages.ILLEGAL_INPUT);
            }
            keyValueMap.put(key, value);
        }

        String unmatched = matcher.replaceAll("").trim();
        if (!unmatched.isEmpty()) {
            throw new InvalidArgumentException(String.format(PlanitExceptionMessages.MISSING_INPUT, unmatched));
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
            throw new EmptyCommandException(PlanitExceptionMessages.MISSING_TASK_INDEX);
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
                throw new IllegalArgumentException(PlanitExceptionMessages.MISSING_TASK_TYPE);
            }

            int taskId = Integer.parseInt(index.substring(1));
            if (taskId < 1 || taskId > taskCount) {
                throw new IndexOutOfBoundsException(String.format(PlanitExceptionMessages.INDEX_OUT_OF_BOUNDS, index));
            }
            return new String[] {taskType, String.valueOf(taskId)};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PlanitExceptionMessages.INVALID_INDEX);
        }
    }
}

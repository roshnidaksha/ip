package planit.messages;

public class PlanitExceptionMessages {
    public static final String INVALID_COMMAND = """
            Invalid command: %s
            Enter "help" to view the list of commands available.""";
    public static final String INVALID_INPUT = "Invalid input";
    public static final String ILLEGAL_INPUT = "Illegal input: Unexpected '/' found in value for key";
    public static final String MISSING_INPUT = "Invalid input: key /%s found with no value.";

    public static final String MISSING_TASK_INDEX = "Task index is required";
    public static final String MISSING_TASK_TYPE = """
            Invalid or missing task type
            Please enter one of the following options:
            t (todo), d (deadline), e (event)""";
    public static final String INDEX_OUT_OF_BOUNDS = "Index out of bounds: %s";
    public static final String INVALID_INDEX = "Index is not a valid number";

    public static final String WRONG_ARGUMENTS = "Please provide the correct number of arguments.";
}

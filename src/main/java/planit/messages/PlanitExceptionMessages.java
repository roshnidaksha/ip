package planit.messages;

/**
 * Contains predefined exception messages used throughout the {@code Planit} application.
 * <p>
 * This class provides static final strings for various exceptions raised during interaction with user.
 * </p>
 */
public class PlanitExceptionMessages {
    public static final String INVALID_COMMAND = """
            Invalid command: %s
            Enter "help" to view the list of commands available.""";
    public static final String ILLEGAL_TASK_INPUT = "Illegal input: Unexpected '/' found in value for key";
    public static final String MISSING_TASK_INPUT = "Invalid input: key /%s found with no value.";

    public static final String MISSING_TASK_INDEX = "Task index is required";
    public static final String MISSING_TASK_TYPE = """
            Invalid or missing task type
            Please enter one of the following options:
            t (todo), d (deadline), e (event)""";
    public static final String INDEX_OUT_OF_BOUNDS = "Index out of bounds: %s";
    public static final String INVALID_INDEX = "Index is not a valid number";

    public static final String WRONG_ARGUMENTS = "Please provide the correct number of arguments.";
    public static final String INVALID_DATE_FORMAT = """
            Invalid date/time format: %s
            Please enter in the format: dd/MM/yyyy HHmm""";
    public static final String INVALID_DATE_RANGE = "End time %s cannot be before start time %s";

    public static final String UNABLE_TO_CREATE_DIR = "Could not create directory: %s";
    public static final String UNABLE_TO_CREATE_FILE = "Could not create file: %s";
    public static final String DUPLICATE_FILE = "File already exists!";
    public static final String UNABLE_TO_READ_FILE = "Could not read file: %s";
    public static final String UNABLE_TO_WRITE_FILE = "Could not write to file: %s";
}

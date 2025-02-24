package planit.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Parses date and time from user input.
 */
public class DateParser {
    private static final DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy[ h:mm a]");
    private static final List<DateTimeFormatter> DATE_TIME_INPUT_FORMATTER = List.of(
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("yyyy/M/d"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),

            DateTimeFormatter.ofPattern("MMM d yyyy[ H:mm]"),
            DateTimeFormatter.ofPattern("MMM d yyyy[ h:mm a]"),

            DateTimeFormatter.ofPattern("yyyy-M-d[ H:mm]"),
            DateTimeFormatter.ofPattern("yyyy-M-d[ h:mm a]"),
            DateTimeFormatter.ofPattern("yyyy/M/d[ H:mm]"),
            DateTimeFormatter.ofPattern("yyyy/M/d[ h:mm a]"),

            DateTimeFormatter.ofPattern("d-M-yyyy[ H:mm]"),
            DateTimeFormatter.ofPattern("d-M-yyyy[ h:mm a]"),
            DateTimeFormatter.ofPattern("d/M/yyyy[ H:mm]"),
            DateTimeFormatter.ofPattern("d/M/yyyy[ h:mm a]")
    );

    /**
     * Parses date and time from user input.
     *
     * @param input User input.
     * @return Parsed date and time.
     */
    public static LocalDateTime parseDateTime(String input) {
        LocalDateTime parsedDateTime = null;
        for (DateTimeFormatter formatter : DATE_TIME_INPUT_FORMATTER) {
            try {
                parsedDateTime = LocalDateTime.parse(input, formatter);
                break;
            } catch (Exception e) {
                try {
                    LocalDate parsedDate = LocalDate.parse(input, formatter);
                    parsedDateTime = parsedDate.atStartOfDay();
                    break;
                } catch (Exception ignored) {
                    // Continue to next formatter
                }
            }
        }
        return parsedDateTime;
    }

    /**
     * Converts date and time to a format that is suitable for storage.
     *
     * @param dateTime Date and time to be converted.
     * @return Date and time in file format.
     */
    public static String toFileFormat(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0 && dateTime.getSecond() == 0 && dateTime.getNano() == 0) {
            return dateTime.format(DATE_OUTPUT_FORMATTER);  // Only format date
        } else {
            return dateTime.format(DATE_TIME_OUTPUT_FORMATTER);  // Format date and time
        }
    }
}

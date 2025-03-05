package planit.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Parses date and time from user input.
 */
public class DateParser {
    private static final DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy[ h:mm a]");
    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("MMM d yyyy", Locale.getDefault()),
            DateTimeFormatter.ofPattern("yyyy-M-d", Locale.getDefault()),
            DateTimeFormatter.ofPattern("yyyy/M/d", Locale.getDefault()),
            DateTimeFormatter.ofPattern("d-M-yyyy", Locale.getDefault()),
            DateTimeFormatter.ofPattern("d/M/yyyy", Locale.getDefault())
    );

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("MMM d yyyy H:mm", Locale.getDefault()),
            DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.getDefault()),
            DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH),

            DateTimeFormatter.ofPattern("yyyy-M-d H:mm", Locale.getDefault()),
            DateTimeFormatter.ofPattern("yyyy-M-d h:mm a", Locale.getDefault()),
            DateTimeFormatter.ofPattern("yyyy-M-d h:mm a", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy/M/d H:mm", Locale.getDefault()),
            DateTimeFormatter.ofPattern("yyyy/M/d h:mm a", Locale.getDefault()),
            DateTimeFormatter.ofPattern("yyyy/M/d h:mm a", Locale.ENGLISH),

            DateTimeFormatter.ofPattern("d-M-yyyy H:mm", Locale.getDefault()),
            DateTimeFormatter.ofPattern("d-M-yyyy h:mm a", Locale.getDefault()),
            DateTimeFormatter.ofPattern("d-M-yyyy h:mm a", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d/M/yyyy H:mm", Locale.getDefault()),
            DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.getDefault()),
            DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.ENGLISH)
    );

    /**
     * Parses date and time from user input.
     *
     * @param input User input.
     * @return Parsed date and time.
     */
    public static LocalDateTime parseDateTime(String input) {
        LocalDateTime parsedDateTime = null;
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDate parsedDate = LocalDate.parse(input, formatter);
                parsedDateTime = parsedDate.atStartOfDay();
                break;
            } catch (Exception ignored) {
                // Continue to next formatter
            }
        }

        if (parsedDateTime == null) {
            for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
                try {
                    parsedDateTime = LocalDateTime.parse(input, formatter);
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

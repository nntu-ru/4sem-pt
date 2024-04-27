package organizer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Позволяет удобно конвертировать LocalDateTime в строку и обратно
 */
public class TimeFormatter {
    public static String format = "yyyy-MM-dd HH:mm";

    public static DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern(format);
    }

    public static String retrieve(LocalDateTime ldt) {
        return ldt.format(formatter());
    }

    public static LocalDateTime retrieve(String string) {
        return LocalDateTime.parse(string, formatter());
    }
}

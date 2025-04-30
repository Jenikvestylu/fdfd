import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public enum LogLevel {
        INFO, WARNING, ERROR
    }

    public static void log(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[" + timestamp + "] [" + level + "] " + message);
    }
}
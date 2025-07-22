import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityLogger {
    private static final String LOG_FILE = "activity_log.txt";
    private static final Object lock = new Object();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String username, String action, String details) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] User: %s | Action: %s | Details: %s", timestamp, username, action, details);
        synchronized (lock) {
            try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                out.println(logEntry);
            } catch (IOException e) {
                // Optionally handle logging errors
                e.printStackTrace();
            }
        }
    }

    public static void log(String username, String action) {
        log(username, action, "");
    }
} 
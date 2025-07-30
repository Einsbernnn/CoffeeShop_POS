import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class ClockPanel extends JPanel {
    private final JLabel clockLabel;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final Timer timer;

    public ClockPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        clockLabel.setForeground(new Color(80, 80, 80));
        add(clockLabel);
        updateClock();
        timer = new Timer(1000, e -> updateClock());
        timer.start();
    }

    private void updateClock() {
        clockLabel.setText(LocalTime.now().format(timeFormatter));
    }

    public void stopClock() {
        timer.stop();
    }
} 
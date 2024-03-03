import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Timer extends JFrame implements Runnable {
    private JLabel timerLabel;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public Timer() {
        setTitle("Timer");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timerLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(timerLabel, BorderLayout.CENTER);

        Thread thread = new Thread(this);
        thread.start();

        setVisible(true);
    }

    @Override
    public void run() {
        while ((minutes * 60 + seconds) < 90) {
            try {
                Thread.sleep(1);
                incrementTime();
                updateTimerLabel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void incrementTime() {
        milliseconds++;
        if (milliseconds == 1000) {
            milliseconds = 0;
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }
        }
    }

    private void updateTimerLabel() {
        timerLabel.setText(String.format("%02d:%02d:%02d", minutes, seconds, milliseconds / 10));
    }

    public static void main(String[] args) {
        new Timer();
    }
}

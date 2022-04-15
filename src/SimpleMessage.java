import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleMessage {

    /**
     * Displays a small frame with a simple message, which can vary depending on the circumstance
     * @param t frame text
     * @param m main message
     */
    public void message(String t, String m) {

        JFrame messageFrame = new JFrame(t);

        JLabel messageText = new JLabel(m);

        JButton ok = new JButton("OK");

        messageFrame.setLayout(new FlowLayout());

        messageFrame.add(messageText);
        messageFrame.add(ok);
        messageFrame.setBounds(0,0,700, 120);
        messageFrame.setLocationRelativeTo(null);
        messageFrame.setVisible(true);

        ok.addActionListener(event -> messageFrame.setVisible(false));
    }
}

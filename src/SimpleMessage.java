import javax.swing.*;
import java.awt.*;

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

    /**
     * Displays a small frame with a simple message, which can vary depending on the circumstance
     * @param t frame text
     * @param m main message
     * @return true if yes is pressed, false if no or "x" button is pressed, and itself if nothing is pressed
     */
    public boolean confirmed (String t, String m) {

        JFrame confirmFrame = new JFrame(t);

        JLabel confirmText = new JLabel(m);

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");

        confirmFrame.setLayout(new FlowLayout());

        confirmFrame.add(confirmText);
        confirmFrame.add(yes);
        confirmFrame.add(no);
        confirmFrame.setBounds(0,0,700, 120);
        confirmFrame.setLocationRelativeTo(null);
        confirmFrame.setVisible(true);
        confirmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        yes.addActionListener(event -> {

            confirmFrame.setVisible(false);
        });

        no.addActionListener(event -> {

            confirmFrame.setVisible(false);
        });

        if (yes.getModel().isPressed())
            return true;
        else if (no.getModel().isPressed())
            return false;
        else
            return false;
    }
}

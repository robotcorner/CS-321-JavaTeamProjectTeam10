import javax.swing.*;
import java.awt.*;

public class SaveMessage {

    static LoginManager loginManager;

    public SaveMessage (LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    /**
     * Displays a small frame with a simple message, which can vary depending on the circumstance
     */
    synchronized public void save() {

        JFrame saveFrame = new JFrame("Warning");

        JLabel saveText = new JLabel("Do you wish to save your progress?");

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");

        saveFrame.setLayout(new FlowLayout());

        saveFrame.add(saveText);
        saveFrame.add(yes);
        saveFrame.add(no);
        saveFrame.setBounds(0,0,700, 120);
        saveFrame.setLocationRelativeTo(null);
        saveFrame.setVisible(true);

        yes.addActionListener(event -> {

            saveFrame.setVisible(false);
            loginManager.save();
            loginManager.logout();
            notify();
        });

        no.addActionListener(event -> {

            saveFrame.setVisible(false);
            loginManager.logout();
            notify();
        });
    }
}

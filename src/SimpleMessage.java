import javax.swing.*;
import java.awt.*;

public class SimpleMessage {

    public void message(String t, String m) {

        JFrame messageFrame = new JFrame(t);

        JTextArea messageText = new JTextArea(m);

        JButton ok = new JButton("OK");

        ok.addActionListener(event -> messageFrame.setVisible(false));

        messageFrame.setLayout(new FlowLayout());

        messageFrame.add(messageText);
        messageFrame.add(ok);
    }
}

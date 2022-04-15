import javax.swing.*;

public class SaveMessage {

    static LoginManager loginManager;

    public SaveMessage (LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    /**
     * Displays a small frame with a simple message, which can vary depending on the circumstance
     */
    public void save() {

        JFrame saveFrame = new JFrame();
        saveFrame.setLocationRelativeTo(null);

        int result = JOptionPane.showConfirmDialog(saveFrame,"Do you want to save your progress?", "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION) {
            loginManager.save();
            loginManager.logout();
        }

        else if (result == JOptionPane.NO_OPTION) {
            loginManager.logout();
        }
    }
}

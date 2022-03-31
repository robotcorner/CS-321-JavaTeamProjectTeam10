import javax.swing.*;

public class MovieManagerView {
    private JPanel moviepanel;
    private JComboBox comboBox1;
    private JTextField textField1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Movie View");
        frame.setSize(1000,1000);
        frame.setContentPane(new MovieManagerView().moviepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MovieManagerView {
    static LoginView accountView;

    public MovieManagerView(LoginView loginView) {
        this.accountView = loginView;

        System.out.println("initialized account view");
    }

    static LoginManager loginManager;

    public MovieManagerView(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    public static void main() {
        JFrame frame = new JFrame("Main");
        JPanel frameP = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        // Build out the TopBar
        // The SEARCH bar for the top middle section
        // JLabel searchBarIcon = new JLabel("Search: ");🔎
        JLabel searchBarIcon = new JLabel("Search: ");
        JTextField searchBar = new JTextField("Search Here");
        searchBar.setMinimumSize(searchBar.getSize());
        searchBar.setPreferredSize(new Dimension(225, 30));
        searchBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchBar.setText("");
            }
        });

        searchBarIcon.setVisible(true);
        searchBar.setVisible(true);
        searchBar.setLocation(frame.getWidth() / 2, 20);
        searchBar.setVisible(true);


        JPanel loginSection = new JPanel();
        loginSection.add(new JLabel("Login/Signup: "));
        JButton loginBtn;
        if (loginManager.verifyLogin() == false) {

            loginBtn = new JButton("Login");
            loginBtn.addActionListener(e -> {
                System.out.println("Pressed Login Button");
                accountView.openLogInView();
            });
        }
        else {

            loginBtn = new JButton("Logout");
            loginBtn.addActionListener(e -> {
                System.out.println("Pressed Logout Button");
                //makes currentuser = null
            });
        }
        JButton signupBtn = new JButton("Sign-up");
        signupBtn.addActionListener(e -> {
            System.out.println("Pressed Sign-Up Button");
            accountView.openSignUpView();
        });

        loginSection.add(loginBtn);
        loginSection.add(signupBtn);
        loginSection.setLocation((frame.getWidth() - 60), 20);
        loginSection.setVisible(true);


        JPanel topBar = new JPanel();
        topBar.add(searchBarIcon);
        topBar.add(searchBar);
        topBar.add(loginSection);
        topBar.setVisible(true);
        JPanel moviePanel = new JPanel();

        // add the two main pieces
        frameP.add(topBar);
        frameP.add(moviePanel);
        frameP.setVisible(true);
        //frame.pack();
        frame.setContentPane(frameP);
        // frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}

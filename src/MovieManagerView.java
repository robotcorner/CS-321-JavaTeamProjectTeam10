import javax.swing.*;


public class MovieManagerView {
    static LoginView accountView;

    public MovieManagerView(LoginView loginView) {
        this.accountView = loginView;
        System.out.println("initialized account view");
    }


    public static void main() {
        JFrame frame = new JFrame("Main");
        JPanel frameP = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        // Build out the TopBar
        // The SEARCH bar for the top middle section
        JTextField searchBar = new JTextField("Search Here");
        searchBar.setVisible(true);
        searchBar.setLocation(frame.getWidth()/2, 20);
        searchBar.setVisible(true);


        JPanel loginSection = new JPanel();
        loginSection.add(new JLabel("Login/Signup: "));
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e-> {
            System.out.println("Pressed Login Button");
            accountView.openLogInView();
        });
        JButton signupBtn = new JButton("Sign-up");
        signupBtn.addActionListener(e-> {
            System.out.println("Pressed Sign-Up Button");
            accountView.openSignUpView();
        });

        loginSection.add(loginBtn);
        loginSection.add(signupBtn);
        loginSection.setLocation((frame.getWidth()-60), 20);
        loginSection.setVisible(true);


        JPanel topBar = new JPanel();
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
